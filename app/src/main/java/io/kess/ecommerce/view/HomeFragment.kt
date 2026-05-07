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

        val productList = listOf(
            Product(R.drawable.img_product1, "Shoes", "TECH", 50),
            Product(R.drawable.img_product2, "Bag", "FASHION", 500),
            Product(R.drawable.img_product3, "Watch", "BEAUTY", 150),
            Product(R.drawable.img_product4, "Shoes", "TECH", 100),
            Product(R.drawable.img_product1, "Headphones", "FASHION", 400),
            Product(R.drawable.img_product2, "Shoes", "BEAUTY", 550),
            Product(R.drawable.img_product3, "Watch", "TECH", 500)
        )

        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = ProductAdapter(productList)

//        val viewPager = view.findViewById<ViewPager2>(R.id.imageSlider)
//        val images = listOf(
//            R.drawable.img_product1,
//            R.drawable.img_product2,
//            R.drawable.img_product3,
//        )
//        viewPager.adapter = ImageSliderAdapter(images)

        val viewAll = view.findViewById<TextView>(R.id.viewAll)
        viewAll.setOnClickListener {
            val fragment = CategoryFragment()
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
        }
        view.findViewById<TextView>(R.id.seeAll).setOnClickListener {
            val intent  = Intent(requireContext(), SplashSaleActivity::class.java)
            startActivity(intent)
        }
    }
}