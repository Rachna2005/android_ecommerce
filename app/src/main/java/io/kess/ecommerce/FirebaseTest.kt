package io.kess.ecommerce

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import io.kess.ecommerce.model.Product
import io.kess.ecommerce.model.ProductDetail
import kotlin.math.log


class FirebaseTest {
    val fireStore = FirebaseFirestore.getInstance()
    fun testFirebaseConnection() {
        val db = FirebaseFirestore.getInstance()
        db.collection("products").get().addOnSuccessListener { result ->
            Log.d("Firebase test", "Connected Successfully!")
        }.addOnFailureListener { e ->
            Log.e("firebase test", "Connection failed: ${e.message}")
        }
    }

    fun getProduct(
        onResult: (List<Product>) -> Unit,
        onFailure: (Exception) -> Unit
    ) {

        Log.d("FIREBASE_TEST", "Fetching products...")

        fireStore.collection("products")
            .get()
            .addOnSuccessListener { result ->

                Log.d("FIREBASE_TEST", "Documents count: ${result.size()}")

                val productList = result.documents.mapNotNull { doc ->

                    Log.d("FIREBASE_TEST", "Document ID: ${doc.id}")

                    doc.toObject(Product::class.java)
                }

                Log.d("FIREBASE_TEST", "Mapped products: ${productList.size}")

                onResult(productList)
            }
            .addOnFailureListener { e ->

                Log.e("FIREBASE_TEST", "Firestore error", e)

                onFailure(e)
            }
    }

    fun getProductDetail(productId: String, onResult: (ProductDetail) -> Unit, onFailure: (Exception) -> Unit){
        Log.d("PRODUCT_DETAIL", "Fetching product: $productId")
        fireStore.collection("products").document(productId).get().addOnSuccessListener {
            productDoc ->
            val product = productDoc.toObject(Product::class.java)
            Log.d("PRODUCT_NAME","Product name: ${product?.name}")
            productDoc.reference.collection("variants").get().addOnSuccessListener {
                variantDoc ->
            }
        }

    }


    fun testFirebaseAuth() {
        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword("test@gmail.com", "123456")
            .addOnSuccessListener {
                Log.d("AUTH_TEST", "Auth Success: ${it.user?.uid}")
            }
            .addOnFailureListener {
                Log.d("AUTH_TEST", "Auth Failed: ${it.message}")
            }
    }

}





