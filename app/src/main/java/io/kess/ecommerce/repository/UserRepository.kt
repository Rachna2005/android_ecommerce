package io.kess.ecommerce.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import io.kess.ecommerce.model.User

class UserRepository {

    fun register(name: String, email: String, password: String) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { result ->
                val uid = result.user!!.uid
                val user = User(id = uid, name = name, email = email)
                FirebaseFirestore.getInstance()
                    .collection("users")
                    .document(uid)
                    .set(user)
            }
    }

    fun login(email: String, password: String, onResult: (User?) -> Unit) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { result ->
                val uid = result.user!!.uid
                FirebaseFirestore.getInstance().collection("users").document(uid).get()
                    .addOnSuccessListener { doc ->
                        val user = doc.toObject(User::class.java)
                        onResult(user)
                    }
            }
            .addOnFailureListener {
                onResult(null)
            }
    }
}