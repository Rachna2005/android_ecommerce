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
    private lateinit var productAdapter: ProductAdapter
    private var type: String = "ALL"
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
        setupClickListener()
        setupRecyclerView()
        observeProducts()
    }

    private fun setupRecyclerView(){
        productAdapter = when(type){
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

    private fun observeProducts(){
        viewModel.products.observe(viewLifecycleOwner){
            products ->
            val result = when (type){
                "DISCOUNT" -> {
                    binding.title.text = "Discount Products"
                    products.filter { (it.discountPercentage ?: 0.0) > 0 }
                }
                "NEW_ARRIVAL" -> {
                    binding.title.text = "New Arrivals"
                   products.sortedByDescending { it.createdAt }
                }
                "ALL" -> {
                    binding.title.text = "All Products"
                   products
                }
                else -> {
                    binding.title.text = "Products"
                    products
                }
            }
            productAdapter.submitList(result)
        }
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