package io.kess.ecommerce.repository

import com.google.firebase.firestore.FirebaseFirestore
import io.kess.ecommerce.model.Product

class ProductRepository {
    val fireStore = FirebaseFirestore.getInstance()

    fun getProduct(onResult: (List<Product>) -> Unit) {
        fireStore.collection("products").get().addOnSuccessListener { result ->
            val productList = result.map { doc ->
                doc.toObject(Product::class.java)
            }
            onResult(productList)
        }.addOnFailureListener {
            onResult(emptyList())
        }
    }
}