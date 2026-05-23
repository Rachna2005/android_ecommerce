package io.kess.ecommerce.model

import android.media.Image

data class Category(
    val id: String = "",
    val name: String = "",
    val productCount: Int = 0,
    val image: Int,
    val alignRight: Boolean
)
