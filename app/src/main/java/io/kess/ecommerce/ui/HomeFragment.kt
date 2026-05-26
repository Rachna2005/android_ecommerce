package io.kess.ecommerce.ui

import BannerAdapter
import android.content.Intent
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import io.kess.ecommerce.R
import io.kess.ecommerce.model.Category
import io.kess.ecommerce.model.Product
import io.kess.ecommerce.ui.adapter.ProductAdapter
import io.kess.ecommerce.view_model.AuthViewModel
import io.kess.ecommerce.view_model.ProductViewModel
import java.util.logging.Handler
import com.google.firebase.Timestamp
import io.kess.ecommerce.view_model.FavoriteViewModel

class HomeFragment : Fragment() {
    private lateinit var runnable: Runnable
    private val handler = android.os.Handler(Looper.getMainLooper())
    private lateinit var viewModel: ProductViewModel
    private lateinit var favoriteViewModel: FavoriteViewModel
    private lateinit var recyclerViewDiscount: RecyclerView
    private lateinit var recyclerViewNewArrival: RecyclerView
    private lateinit var recyclerViewAll: RecyclerView
    private lateinit var imgSlide: ViewPager2
    private lateinit var discountAdapter: ProductAdapter
    private lateinit var newArrivalAdapter: ProductAdapter
    private lateinit var allAdapter: ProductAdapter
    private var productList: List<Product> = emptyList()
    private var favorite: Set<String> = emptySet()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ProductViewModel::class.java]
        favoriteViewModel = ViewModelProvider(this)[FavoriteViewModel::class.java]
        favoriteViewModel.favorite.observe(viewLifecycleOwner) {
            favorite = it.toSet()
        }
        initView(view)
        dummyData()
//        setUpBanner()
        setupRecyclerView()
        setupClickListeners(view)
    }

    private fun dummyData() {
        productList = listOf(
            Product(
                id = "p001",
                image = "https://images.unsplash.com/photo-1542291026-7eec264c27ff",
                name = "Nike Running Shoes",
                categoryId = "shoes",
                price = 120.0,
                discountPercentage = 10.0,
                description = "Comfortable running shoes for daily training.",
                createdAt = Timestamp.now()
            ),
            Product(
                id = "p002",
                image = "https://images.unsplash.com/photo-1523275335684-37898b6baf30",
                name = "Adidas Sneakers",
                categoryId = "shoes",
                price = 95.0,
                discountPercentage = 12.0,
                description = "Stylish sneakers for casual wear.",
                createdAt = Timestamp.now()
            ),
            Product(
                id = "p003",
                image = "https://images.unsplash.com/photo-1600180758890-6b94519a8ba6",
                name = "Puma Sport Shoes",
                categoryId = "shoes",
                price = 80.0,
                discountPercentage = null,
                description = "Lightweight shoes for sports activities.",
                createdAt = Timestamp.now()
            ),
            Product(
                id = "p004",
                image = "https://images.unsplash.com/photo-1521572163474-6864f9cf17ab",
                name = "White Basic T-Shirt",
                categoryId = "clothing",
                price = 20.0,
                discountPercentage = 5.0,
                description = "Simple cotton t-shirt for everyday wear.",
                createdAt = Timestamp.now()
            ),
            Product(
                id = "p005",
                image = "https://images.unsplash.com/photo-1520975916090-3105956dac38",
                name = "Black Hoodie",
                categoryId = "clothing",
                price = 45.0,
                discountPercentage = 15.0,
                description = "Warm hoodie for winter season.",
                createdAt = Timestamp.now()
            ),
            Product(
                id = "p006",
                image = "https://images.unsplash.com/photo-1517336714731-489689fd1ca8",
                name = "MacBook Laptop",
                categoryId = "electronics",
                price = 1200.0,
                discountPercentage = 8.0,
                description = "High-performance laptop for work and study.",
                createdAt = Timestamp.now()
            ),
            Product(
                id = "p007",
                image = "https://images.unsplash.com/photo-1510557880182-3d4d3cba35a5",
                name = "Smart Watch",
                categoryId = "electronics",
                price = 199.0,
                discountPercentage = 10.0,
                description = "Track your fitness and notifications easily.",
                createdAt = Timestamp.now()
            ),
            Product(
                id = "p008",
                image = "https://images.unsplash.com/photo-1585386959984-a4155224a1ad",
                name = "Wireless Headphones",
                categoryId = "electronics",
                price = 150.0,
                discountPercentage = 20.0,
                description = "Noise-cancelling wireless headphones.",
                createdAt = Timestamp.now()
            ),
            Product(
                id = "p009",
                image = "https://images.unsplash.com/photo-1587829741301-dc798b83add3",
                name = "Leather Wallet",
                categoryId = "accessories",
                price = 35.0,
                discountPercentage = 10.0,
                description = "Premium leather wallet with card slots.",
                createdAt = Timestamp.now()
            ),
            Product(
                id = "p010",
                image = "https://images.unsplash.com/photo-1503602642458-232111445657",
                name = "Desk Lamp",
                categoryId = "home",
                price = 25.0,
                discountPercentage = 5.0,
                description = "Modern LED desk lamp with adjustable brightness.",
                createdAt = Timestamp.now()
            )
        )
    }

    private fun initView(view: View) {
        recyclerViewDiscount = view.findViewById(R.id.view_discount)
        recyclerViewNewArrival = view.findViewById(R.id.view_new_arrival)
        recyclerViewAll = view.findViewById(R.id.view_all_product)

        imgSlide = view.findViewById(R.id.viewPagerBanner)
    }

    private fun setUpBanner() {
        val imageList = listOf(
            "https://res.cloudinary.com/dcao8vmuc/image/upload/v1779176445/banner3_splvfi.png",
            "https://res.cloudinary.com/dcao8vmuc/image/upload/v1779176445/banner3_splvfi.png",
            "https://res.cloudinary.com/dcao8vmuc/image/upload/v1779176444/banner2_kwjqie.png"
        )

        imgSlide.adapter = BannerAdapter(imageList)

        handler.postDelayed(object : Runnable {
            override fun run() {

                val current = imgSlide.currentItem
                val next = if (current == imageList.size - 1) 0 else current + 1

                imgSlide.currentItem = next

                handler.postDelayed(this, 3000)
            }
        }, 3000)
    }

    private fun setupRecyclerView() {
        Log.d("PRODUCT_DEBUG", "setupRecyclerView called")
        discountAdapter = ProductAdapter(favorite) { product ->
            favoriteViewModel.toggleFavorite(product.id)
        }
        recyclerViewDiscount.adapter = discountAdapter
        recyclerViewDiscount.layoutManager =
            LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )

        newArrivalAdapter = ProductAdapter(favorite) { product ->
            favoriteViewModel.toggleFavorite(product.id)
        }
        recyclerViewNewArrival.adapter = newArrivalAdapter
        recyclerViewNewArrival.layoutManager =
            GridLayoutManager(requireContext(), 2)
        recyclerViewNewArrival.isNestedScrollingEnabled = false

        allAdapter = ProductAdapter(favorite) { product ->
            favoriteViewModel.toggleFavorite(product.id)
        }
        recyclerViewAll.adapter = allAdapter
        recyclerViewAll.layoutManager =
            LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )

//        viewModel.products.observe(viewLifecycleOwner) { products ->
//            Log.d("PRODUCT_DEBUG", "Observer triggered")
//            val discountList = products.filter { (it.discountPercentage ?: 0.0) > 0 }.take(5)
//            val newArrivalList =
//                products
//                    .sortedByDescending { it.createdAt?.seconds ?: 0 }
//                    .take(4)
//            discountAdapter.submitList(discountList)
//            newArrivalAdapter.submitList(newArrivalList)
//            allAdapter.submitList(products)
//        }

        // test with dummy data
        val discountList = productList.filter { (it.discountPercentage ?: 0.0) > 0 }.take(5)
        val newArrivalList =
            productList
                .take(4)
        discountAdapter.submitList(discountList)
        newArrivalAdapter.submitList(newArrivalList)
        allAdapter.submitList(productList)
    }

    private fun setupClickListeners(view: View) {

        val search = view.findViewById<ImageView>(R.id.search)

        search.setOnClickListener {
            (activity as MainActivity).navigation(SearchFragment())
        }

        val discountMore = view.findViewById<TextView>(R.id.discount_all)
        discountMore.setOnClickListener {
            openProductList("DISCOUNT")
//            (activity as MainActivity).navigation(ProductListFragment())
        }
        val newMore = view.findViewById<TextView>(R.id.new_more)
        newMore.setOnClickListener {
            openProductList("NEW_ARRIVAL")
        }
        val allMore = view.findViewById<TextView>(R.id.all_seeMore)
        allMore.setOnClickListener {
            openProductList("ALL")
        }
    }

    private fun openProductList(type: String) {
        val fragment = ProductListFragment().apply {
            arguments = Bundle().apply {
                putString("TYPE", type)
            }
        }
        (activity as MainActivity).navigation(fragment)
    }


    override fun onDestroyView() {
        super.onDestroyView()

        handler.removeCallbacksAndMessages(null)
    }
}