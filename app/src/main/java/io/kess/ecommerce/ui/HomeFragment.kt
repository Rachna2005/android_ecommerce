package io.kess.ecommerce.ui

import BannerAdapter
import android.content.Intent
import android.os.Bundle
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import io.kess.ecommerce.R
import io.kess.ecommerce.model.Category
import io.kess.ecommerce.model.Product
import io.kess.ecommerce.ui.adapter.ProductAdapter
import java.util.logging.Handler

class HomeFragment : Fragment() {

    private lateinit var runnable: Runnable
    private val handler = android.os.Handler(Looper.getMainLooper())

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
        val recyclerViewDiscount = view.findViewById<RecyclerView>(R.id.view_discount)
        val recyclerViewNewArrival = view.findViewById<RecyclerView>(R.id.view_new_arrival)
        val recyclerViewAll = view.findViewById<RecyclerView>(R.id.view_all_product)

        val imageList = listOf(
            "https://picsum.photos/800/400?1",
            "https://picsum.photos/800/400?2",
            "https://picsum.photos/800/400?3"
        )

        val imgSlide = view.findViewById<ViewPager2>(R.id.viewPagerBanner)
        imgSlide.adapter = BannerAdapter(imageList)

        runnable = Runnable {

            val current =imgSlide.currentItem
            val next = if (current == imageList.size - 1) 0 else current + 1

            imgSlide.currentItem = next
        }

        handler.postDelayed(object : Runnable {
            override fun run() {

                val current = imgSlide.currentItem
                val next = if (current == imageList.size - 1) 0 else current + 1

                imgSlide.currentItem = next

                handler.postDelayed(this, 3000)
            }
        }, 3000)

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
                categoryId = "1",
                price = 120.0,
                discountPercentage = 10.0,
                description = "Comfortable running shoes"
            ),

            Product(
                id = "2",
                image = "https://images.unsplash.com/photo-1521572163474-6864f9cf17ab",
                name = "Oversized T-Shirt",
                categoryId = "2",
                price = 35.0,
                discountPercentage = null,
                description = "Cotton oversized t-shirt"
            ),

            Product(
                id = "3",
                image = "https://images.unsplash.com/photo-1556821840-3a63f95609a7",
                name = "Black Hoodie",
                categoryId = "3",
                price = 60.0,
                discountPercentage = 15.0,
                description = "Warm black hoodie"
            ),
            Product(
                id = "4",
                image = "https://images.unsplash.com/photo-1541099649105-f69ad21f3246",
                name = "Cargo Pants",
                categoryId = "4",
                price = 55.0,
                discountPercentage = 5.0,
                description = "Stylish cargo pants"
            ),

            Product(
                id = "5",
                image = "https://images.unsplash.com/photo-1600185365483-26d7a4cc7519",
                name = "Adidas Sneakers",
                categoryId = "1",
                price = 95.0,
                discountPercentage = null,
                description = "Classic adidas sneakers"
            )
        )

        // Display Discount product
        val discountList = productList.filter { (it.discountPercentage ?:0.0)>0 }.take(5)
        recyclerViewDiscount.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerViewDiscount.adapter = ProductAdapter(discountList, ProductCardType.DISCOUNT)

        // Display new arrival product
        val newArrivalList = productList.sortedByDescending { it.createdAt }.take(4)
        recyclerViewNewArrival.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerViewNewArrival.adapter = ProductAdapter(newArrivalList, ProductCardType.NORMAL)

        // Display all product
        recyclerViewAll.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.HORIZONTAL, false)
        recyclerViewAll.adapter = ProductAdapter(productList.take(4), ProductCardType.NORMAL)

        // Navigation
        val search = view.findViewById<ImageView>(R.id.search)
        search.setOnClickListener {
            (activity as MainActivity).navigation(SearchFragment())
        }
        val discountMore = view.findViewById<TextView>(R.id.seeAll)
        discountMore.setOnClickListener {
            (activity as MainActivity).navigation(DiscountFragment())
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()

        handler.removeCallbacksAndMessages(null)
    }
}