package io.kess.ecommerce.view_model

import androidx.lifecycle.ViewModel
import io.kess.ecommerce.model.User
import io.kess.ecommerce.repository.UserRepository
import javax.security.auth.callback.Callback

class UserViewModel : ViewModel() {
    private val repository = UserRepository()

    fun register(name: String, email: String, password: String){
        repository.register(name,email,password)
    }
    fun login(email: String, password: String, callBack: (User?) -> Unit){
        repository.login(email, password, callBack)
    }
}
