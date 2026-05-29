//package io.kess.ecommerce.test
//
//import android.os.Bundle
//import android.util.Log
//import androidx.activity.enableEdgeToEdge
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.content.ContentProviderCompat.requireContext
//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat
//import androidx.lifecycle.ViewModelProvider
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import io.kess.ecommerce.R
//import io.kess.ecommerce.ui.adapter.ProductAdapter
//import io.kess.ecommerce.view_model.AuthViewModel
//import io.kess.ecommerce.view_model.FavoriteViewModel
//import io.kess.ecommerce.view_model.ProductViewModel
//
//class HomeTestActivity : AppCompatActivity() {
//    private lateinit var recyclerViewAll: RecyclerView
//    private lateinit var allAdapter: ProductAdapter
//    private lateinit var favoriteViewModel: FavoriteViewModel
//    private lateinit var viewModel: ProductViewModel
//    private var favorite: Set<String> = emptySet()
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.test)
//        viewModel = ViewModelProvider(this)[ProductViewModel::class.java]
//        favoriteViewModel = ViewModelProvider(this)[FavoriteViewModel::class.java]
//        viewModel.loadAllProducts()
//
//        allAdapter = ProductAdapter(emptySet()) { product ->
//            favoriteViewModel.toggleFavorite(product.id)
//        }
//
//        recyclerViewAll.adapter = allAdapter
//        recyclerViewAll.layoutManager =
//            LinearLayoutManager(
//                this,
//                LinearLayoutManager.HORIZONTAL,
//                false
//            )
//
//        viewModel.products.observe(this) { products ->
//            allAdapter.submitList(products)
//        }
//
//        favoriteViewModel.favorite.observe(this) {
//            favorite = it
//            allAdapter.updateFavorites(favorite)
//        }
//    }
//    private fun openProductDetail(productId: String){
//
//    }
//}