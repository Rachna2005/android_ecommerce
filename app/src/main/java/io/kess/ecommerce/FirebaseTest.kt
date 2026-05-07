package io.kess.ecommerce
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.math.log


class FirebaseTest {
    fun testFirebaseConnection(){
        val db = FirebaseFirestore.getInstance()
        db.collection("products").get().addOnSuccessListener {
            result -> Log.d("Firebase test", "Connected Successfully!")
        }.addOnFailureListener { e ->
            Log.e("firebase test", "Connection failed: ${e.message}")
        }
    }
}





