//package io.kess.ecommerce
//
//import android.os.Bundle
//
//import androidx.appcompat.app.AppCompatActivity
//
//import android.util.Log
//import android.widget.Button
//import android.widget.TextView
//
//data class Product(
//    val id: String,
//    val title: String,
//    val price: Double
//)
//
//
//class TestActivity : AppCompatActivity() {
//    private val cartItems = mutableListOf<String>()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.test)
//
//        val productTitle = findViewById<TextView>(R.id.productTitle)
//        val cartCount = findViewById<TextView>(R.id.cartCount)
//        val addToCartBtn = findViewById<Button>(R.id.addToCartBtn)
//
//        val product = Product(
//            id = "P101",
//            title = "Nike Shoes",
//            price = 99.99
//        )
//        productTitle.text = product.title
//
//        addToCartBtn.setOnClickListener {
//
//            Log.d("Cart", "Button clicked")
//
//            addToCart(product)
//
//            cartCount.text = "Cart Items: ${cartItems.size}"
//        }
//    }
//
//    private fun addToCart(product: Product) {
//
//        Log.d("Cart", "Adding product = ${product.title}")
//
//        cartItems.add(product.id)
//
//        Log.d("Cart", "Current cart size = ${cartItems.size}")
//    }
//
//}
//
//
////        val test = FirebaseTest()
////        test.testFirebaseConnection()
////        test.testFirebaseAuth()
////        test.getProduct(onResult = { products ->
////            Log.d("TEST", "Success: ${products.size}")
////        },
////            onFailure = { e ->
////                Log.e("TEST", "Failed", e)
////            })