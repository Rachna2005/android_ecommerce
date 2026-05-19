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
import io.kess.ecommerce.view_model.ProductViewModel
import java.util.logging.Handler

class HomeFragment : Fragment() {
    private lateinit var runnable: Runnable
    private val handler = android.os.Handler(Looper.getMainLooper())
    private lateinit var viewModel: ProductViewModel
    private lateinit var recyclerViewDiscount: RecyclerView
    private lateinit var recyclerViewNewArrival: RecyclerView
    private lateinit var recyclerViewAll: RecyclerView
    private lateinit var imgSlide: ViewPager2

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

        initView(view)
        setUpBanner()
        setupDiscountProducts()
        setupNewArrivalProducts()
        setupAllProducts()
        setupClickListeners(view)
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

    private fun setupDiscountProducts() {

        recyclerViewDiscount.layoutManager =
            LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
        viewModel.products.observe(viewLifecycleOwner) { products ->
            val discountList =
                products.filter { (it.discountPercentage ?: 0.0) > 0 }
                    .take(5)
            recyclerViewDiscount.adapter =
                ProductAdapter(discountList, ProductCardType.DISCOUNT)
        }
    }

    private fun setupNewArrivalProducts() {

        recyclerViewNewArrival.layoutManager =
            GridLayoutManager(requireContext(), 2)
        viewModel.products.observe(viewLifecycleOwner) { products ->
            val newArrivalList =
                products
                    .sortedByDescending { it.createdAt }
                    .take(4)
            recyclerViewNewArrival.adapter =
                ProductAdapter(newArrivalList, ProductCardType.NORMAL)
        }

    }

    private fun setupAllProducts() {

        recyclerViewAll.layoutManager =
            LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )

        viewModel.products.observe(viewLifecycleOwner) { products ->

            recyclerViewAll.adapter =
                ProductAdapter(
                    products.take(4),
                    ProductCardType.NORMAL
                )
        }
    }

    private fun setupClickListeners(view: View) {

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