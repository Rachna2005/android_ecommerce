package io.kess.ecommerce

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.kess.ecommerce.adapter.ProductAdapter
import io.kess.ecommerce.model.Product

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        val productList = listOf(
            Product(R.drawable.product1, "Elite Chrono", "FASHION", 42),
            Product(R.drawable.product2, "Smart Watch", "TECH", 120),
            Product(R.drawable.product3, "Running Shoes", "SPORT", 80),
            Product(R.drawable.product4, "Leather Bag", "FASHION", 60)
        )

        // 🔥 IMPORTANT: grid layout (2 columns like shop apps)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        recyclerView.adapter = ProductAdapter(productList)

    }
}