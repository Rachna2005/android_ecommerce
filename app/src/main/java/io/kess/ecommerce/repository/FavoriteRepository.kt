package io.kess.ecommerce.repository

import com.google.firebase.firestore.FirebaseFirestore
import io.kess.ecommerce.model.Favorite
import io.kess.ecommerce.model.Product
import io.kess.ecommerce.util.UserSession

class FavoriteRepository {
    val db = FirebaseFirestore.getInstance()
    val userId = UserSession.currentUser!!.id

    fun toggleFavorite(productId: String) {
        val favorite =
            db.collection("users").document(userId).collection("favorites").document(productId)
        favorite.get()
            .addOnSuccessListener { doc ->
                if (doc.exists()) {
                    favorite.delete()
                } else {
                    favorite.set(mapOf("productId" to productId))
                }
            }
    }

    fun getAllFavorite(onResult: (List<String>) -> Unit) {
        db.collection("users").document(userId).collection("favorites").get()
            .addOnSuccessListener { result ->
                val ids = result.documents.map {
                    it.id
                }
                onResult(ids)
            }
    }
}