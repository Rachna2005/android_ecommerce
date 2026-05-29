package io.kess.ecommerce.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.kess.ecommerce.R
import io.kess.ecommerce.ui.adapter.CartAdapter
import io.kess.ecommerce.ui.adapter.ColorAdapter
import io.kess.ecommerce.ui.adapter.ProductAdapter
import io.kess.ecommerce.view_model.CartViewModel
import io.kess.ecommerce.view_model.FavoriteViewModel
import io.kess.ecommerce.view_model.ProductViewModel

class CartFragment : Fragment() {
    private lateinit var cartViewModel: CartViewModel
    private lateinit var favoriteViewModel: FavoriteViewModel
    private lateinit var cartAdapter: CartAdapter
    private lateinit var cartRecyclerView: RecyclerView
    private lateinit var num: TextView
    private lateinit var subTotal: TextView
    private lateinit var total: TextView
    private lateinit var totalCheckout: TextView
    private var favorite: Set<String> = emptySet()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cartViewModel =
            ViewModelProvider(requireActivity())[CartViewModel::class.java]
        favoriteViewModel =
            ViewModelProvider(requireActivity())[FavoriteViewModel::class.java]
        cartViewModel.loadCart()
        initView(view)
        setupRecyclerView()
        cartViewModel.cartItems.observe(viewLifecycleOwner){cart ->
            cartAdapter.submitList(cart)
            val totalPrice =
                cart.sumOf {
                    it.price * it.quantity
                }
            val totalItems =
                cart.sumOf {
                    it.quantity
                }

            num.text = "$totalItems"

            subTotal.text =
                "$${String.format("%.2f", totalPrice)}"

            total.text =
                "$${String.format("%.2f", totalPrice)}"

            totalCheckout.text =
                "$${String.format("%.2f", totalPrice)}"
        }
        favoriteViewModel.favorite.observe(viewLifecycleOwner) {

            favorite = it

            cartAdapter.updateFavorites(favorite)
        }
    }

    private fun initView(view: View) {
        cartRecyclerView = view.findViewById(R.id.recyclerView)
        num = view.findViewById(R.id.num)
        subTotal = view.findViewById(R.id.subTotal)
        total = view.findViewById(R.id.total)
        totalCheckout = view.findViewById(R.id.totalCheckout)
    }

    private fun setupRecyclerView() {
        cartAdapter = CartAdapter(favorite, onFavoriteClick = { cartItem ->
            favoriteViewModel.toggleFavorite(cartItem.productId)
        }, onProductClick = { cartItem ->
            openProductDetail(cartItem.productId)
        }, onIncrease = { cartItem ->
            cartViewModel.increaseQuantity(cartItem.id)
        }, onDecrease = { cartItem ->
            cartViewModel.decreaseQuantity(cartItem.id, cartItem.quantity)
        }, onDelete = { cartItem ->
            cartViewModel.deleteCart(cartItem.id)
        }
        )
        cartRecyclerView.adapter = cartAdapter
        cartRecyclerView.layoutManager =
            LinearLayoutManager(requireContext())
    }

    private fun openProductDetail(productId: String) {
        val fragment = ProductDetailFragment().apply {
            arguments = Bundle().apply {
                putString("ID", productId)
            }
        }
        (activity as MainActivity).navigation(fragment)
    }

}