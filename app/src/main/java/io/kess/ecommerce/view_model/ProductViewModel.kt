package io.kess.ecommerce.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.kess.ecommerce.model.Product
import io.kess.ecommerce.repository.ProductRepository

class ProductViewModel : ViewModel() {
    private val repository = ProductRepository()
    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> = _products

    fun loadAllProducts() {
        repository.getProduct(onResult = { data ->
            _products.value = data
        }, onFailure = { e ->
            Log.d("GET_ALL_PRODUCT", e.message.toString())

        })
    }
}

