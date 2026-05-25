package io.kess.ecommerce.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.R
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.kess.ecommerce.databinding.FragmentDiscountScreenBinding
import io.kess.ecommerce.databinding.FragmentDisplayProductBinding
import io.kess.ecommerce.model.Product
import io.kess.ecommerce.ui.adapter.ProductAdapter
import io.kess.ecommerce.view_model.AuthViewModel
import io.kess.ecommerce.view_model.ProductViewModel
import androidx.core.widget.addTextChangedListener
import com.google.firebase.Timestamp

class ProductListFragment : Fragment() {
    private var _binding: FragmentDisplayProductBinding? = null
    private val binding get() = _binding!!
    private lateinit var productAdapter: ProductAdapter
    private var type: String = "ALL"
    private var productList = listOf<Product>()
    private lateinit var viewModel: ProductViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        type = arguments?.getString("TYPE") ?: "ALL"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDisplayProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ProductViewModel::class.java]
        dummyData()
        setupClickListener()
        setupRecyclerView()
        observeProducts()
        setupSearch()
    }

    private fun dummyData(){
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

    private fun setupSearch() {
        binding.search.addTextChangedListener { editable ->
            val query = editable.toString().trim()
            if (query.isEmpty()) {
                productAdapter.submitList(productList)
            } else {
                val filtered = productList.filter {
                    it.name.contains(query, ignoreCase = true)
                }
                productAdapter.submitList(filtered)
            }
        }
    }

    private fun setupRecyclerView() {
        productAdapter = when (type) {
            "DISCOUNT" -> {
                ProductAdapter(ProductCardType.DISCOUNT)
            }

            else -> {
                ProductAdapter(ProductCardType.NORMAL)
            }
        }
        binding.listProduct.apply {
            adapter = productAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    private fun observeProducts() {
//        viewModel.products.observe(viewLifecycleOwner) { products ->
//            val result = when (type) {
//                "DISCOUNT" -> {
//                    binding.title.text = "Discount Products"
//                    products.filter { (it.discountPercentage ?: 0.0) > 0 }
//                }
//
//                "NEW_ARRIVAL" -> {
//                    binding.title.text = "New Arrivals"
//                    products.sortedByDescending { it.createdAt?.seconds ?: 0 }
//                }
//
//                "ALL" -> {
//                    binding.title.text = "All Products"
//                    products
//                }
//
//                else -> {
//                    binding.title.text = "Products"
//                    products
//                }
//            }
//            productList = result
//            productAdapter.submitList(result)
//        }
            val result = when (type) {
                "DISCOUNT" -> {
                    binding.title.text = "Discount Products"
                    productList.filter { (it.discountPercentage ?: 0.0) > 0 }
                }

                "NEW_ARRIVAL" -> {
                    binding.title.text = "New Arrivals"
                    productList.sortedByDescending { it.createdAt?.seconds ?: 0 }
                }

                "ALL" -> {
                    binding.title.text = "All Products"
                    productList
                }

                else -> {
                    binding.title.text = "Products"
                    productList
                }
            }
            productList = result
            productAdapter.submitList(result)

    }

    private fun setupClickListener() {
        binding.backBtn.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        binding.btnCart.setOnClickListener {
            parentFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            (activity as MainActivity).replace(CartFragment())
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).showButtonNav(show = false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as MainActivity).showButtonNav(show = true)
    }
}