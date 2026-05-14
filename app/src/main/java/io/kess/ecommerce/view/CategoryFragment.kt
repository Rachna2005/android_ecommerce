package io.kess.ecommerce.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.kess.ecommerce.R
//import io.kess.ecommerce.adapter.ProductAdapter
import io.kess.ecommerce.model.Product

class CategoryFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

//        val productList = listOf(
//            Product(R.drawable.img_product1, "Shoes", "TECH", 50),
//            Product(R.drawable.img_product2, "Bag", "FASHION", 500),
//            Product(R.drawable.img_product3, "Watch", "BEAUTY", 150),
//            Product(R.drawable.img_product4, "Shoes", "TECH", 100),
//            Product(R.drawable.img_product1, "Headphones", "FASHION", 400),
//            Product(R.drawable.img_product2, "Shoes", "BEAUTY", 550),
//            Product(R.drawable.img_product3, "Watch", "TECH", 500)
//        )

        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
//        recyclerView.adapter = ProductAdapter(productList)
    }

}