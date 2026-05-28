package io.kess.ecommerce.model

import android.media.Image
import com.google.firebase.Timestamp

data class Category(
    val id: String = "",
    val name: String = "",
    val productCount: Int = 0,
    val image: String = "",
    val alignRight: Boolean = true,
    val createAt: Timestamp = Timestamp.now()
)
