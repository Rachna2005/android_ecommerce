package io.kess.ecommerce.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.kess.ecommerce.R
import io.kess.ecommerce.adapter.ProductAdapter
import io.kess.ecommerce.model.Category
import io.kess.ecommerce.model.Product

class HomeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.viewFlashSale)

        val categoryList = listOf(
            Category(
                id = "1",
                name = "Shoes"
            ),

            Category(
                id = "2",
                name = "T-Shirt"
            ),

            Category(
                id = "3",
                name = "Hoodie"
            ),

            Category(
                id = "4",
                name = "Pants"
            )
        )

        val productList = listOf(

            Product(
                id = "1",
                image = "https://images.unsplash.com/photo-1542291026-7eec264c27ff",
                name = "Nike Air Max",
                categoryID = "1",
                price = 120.0,
                discountPercentage = 10.0,
                description = "Comfortable running shoes"
            ),

            Product(
                id = "2",
                image = "https://images.unsplash.com/photo-1521572163474-6864f9cf17ab",
                name = "Oversized T-Shirt",
                categoryID = "2",
                price = 35.0,
                discountPercentage = null,
                description = "Cotton oversized t-shirt"
            ),

            Product(
                id = "3",
                image = "https://images.unsplash.com/photo-1556821840-3a63f95609a7",
                name = "Black Hoodie",
                categoryID = "3",
                price = 60.0,
                discountPercentage = 15.0,
                description = "Warm black hoodie"
            ),

            Product(
                id = "4",
                image = "https://images.unsplash.com/photo-1541099649105-f69ad21f3246",
                name = "Cargo Pants",
                categoryID = "4",
                price = 55.0,
                discountPercentage = 5.0,
                description = "Stylish cargo pants"
            ),

            Product(
                id = "5",
                image = "https://images.unsplash.com/photo-1600185365483-26d7a4cc7519",
                name = "Adidas Sneakers",
                categoryID = "1",
                price = 95.0,
                discountPercentage = null,
                description = "Classic adidas sneakers"
            )
        )


        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = ProductAdapter(productList, categoryList)

//        val viewPager = view.findViewById<ViewPager2>(R.id.imageSlider)
//        val images = listOf(
//            R.drawable.img_product1,
//            R.drawable.img_product2,
//            R.drawable.img_product3,
//        )
//        viewPager.adapter = ImageSliderAdapter(images)

//        val viewAll = view.findViewById<TextView>(R.id.viewAll)
//        viewAll.setOnClickListener {
//            val fragment = CategoryFragment()
//            requireActivity().supportFragmentManager.beginTransaction()
//                .replace(R.id.container, fragment).commit()
//        }

        view.findViewById<TextView>(R.id.seeAll).setOnClickListener {
            val intent = Intent(requireContext(), SplashSaleActivity::class.java)
            startActivity(intent)
        }
    }
}