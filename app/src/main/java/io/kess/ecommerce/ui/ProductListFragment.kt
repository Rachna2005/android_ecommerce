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


class ProductListFragment : Fragment() {
    private var _binding: FragmentDisplayProductBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ProductViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        viewModel.products.observe(viewLifecycleOwner) { products ->
            displayProductCard(products)
        }
        binding.backBtn.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        binding.btnCart.setOnClickListener {
            parentFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            (activity as MainActivity).replace(CartFragment())
        }
    }

    private fun displayProductCard(allProducts: List<Product>) {
        val type = arguments?.getString("TYPE")
        binding.listProduct.layoutManager = GridLayoutManager(requireContext(), 2)
        when (type) {
            "DISCOUNT" -> {
                binding.title.text = "Discount Products"
                val result = allProducts.filter { (it.discountPercentage ?: 0.0) > 0 }
                binding.listProduct.adapter = ProductAdapter(result, ProductCardType.DISCOUNT)
            }

            "NEW_ARRIVAL" -> {
                binding.title.text = "New Arrivals"
                val result = allProducts.sortedByDescending { it.createdAt }
                binding.listProduct.adapter = ProductAdapter(result, ProductCardType.NORMAL)
            }

            "ALL" -> {
                binding.title.text = "All Products"
                binding.listProduct.adapter = ProductAdapter(allProducts, ProductCardType.NORMAL)
            }

            else -> {
                binding.title.text = "Products"
                binding.listProduct.adapter = ProductAdapter(allProducts, ProductCardType.NORMAL)
            }
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