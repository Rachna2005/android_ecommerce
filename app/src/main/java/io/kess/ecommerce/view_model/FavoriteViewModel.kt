package io.kess.ecommerce.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import io.kess.ecommerce.model.Favorite
import io.kess.ecommerce.repository.FavoriteRepository
import io.kess.ecommerce.ui.adapter.ProductAdapter

class FavoriteViewModel : ViewModel() {
    private val repository = FavoriteRepository()
    private val _favorites = MutableLiveData<List<String>>()
    val favorite: LiveData<List<String>> = _favorites

    init {
        loadFavorite()
    }

    fun toggleFavorite(productId: String) {
        repository.toggleFavorite(productId)
    }

    fun loadFavorite() {
        repository.getAllFavorite(onResult = { result ->
            _favorites.value = result
        })
    }
}