package io.kess.ecommerce.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.kess.ecommerce.model.User
import io.kess.ecommerce.repository.AuthRepository
import io.kess.ecommerce.util.UserSession

class AuthViewModel : ViewModel() {
    private val repository = AuthRepository()
    private val _authData = MutableLiveData<User?>()
    val authData: LiveData<User?> = _authData

    fun register(name: String, email: String, password: String) {
        repository.register(name, email, password, onSuccess = { result ->
            UserSession.currentUser = result
            _authData.value = result
        }, onFailure = { _authData.value = null })
    }

    fun login(email: String, password: String) {
        repository.login(
            email,
            password,
            onSuccess = { user ->


                UserSession.currentUser = user

                _authData.value = user
            },
            onFailure = { _authData.value = null })
    }

    fun checkSession() {
        repository.getCurrentUser(
            onSuccess = { user ->
                UserSession.currentUser = user
                _authData.value = user
            },
            onFailure = {
                _authData.value = null
            })
    }
}
