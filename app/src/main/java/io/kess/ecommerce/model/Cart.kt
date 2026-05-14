package io.kess.ecommerce.model

data class Cart(
    val id: String = "",
    val productId: String = "",
    val quantity: Int = 1,
    val selectedSize: String = "",
    val selectedColor: String = "",
)