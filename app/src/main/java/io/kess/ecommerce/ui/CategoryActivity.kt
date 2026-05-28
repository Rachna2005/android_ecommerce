//package io.kess.ecommerce.ui
//
//import android.os.Bundle
//import androidx.activity.enableEdgeToEdge
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat
//import androidx.recyclerview.widget.GridLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import io.kess.ecommerce.R
//import io.kess.ecommerce.model.Category
//import io.kess.ecommerce.ui.adapter.CategoryAdapter
//
//class CategoryActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.fragment_category)
//        val category = listOf(
//            Category("1", "New Arrivals", 200, R.drawable.img_new_arrivals, false),
//            Category("2", "Clothes", 200, R.drawable.img_clothes, true),
//            Category("3", "Bags", 200, R.drawable.img_bags, false),
//            Category("4", "Shoes", 200, R.drawable.img_shoes, true),
//            Category("5", "Electronics", 200, R.drawable.img_electronics, false)
//        )
//        val adapter = CategoryAdapter(category)
//        val cardDisplay = findViewById<RecyclerView>(R.id.recyclerCategory)
//        cardDisplay.adapter = adapter
//        cardDisplay.layoutManager =  GridLayoutManager(this, 1)
//    }
//}
//
//
//
