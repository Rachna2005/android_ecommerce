package io.kess.ecommerce.model

data class CartItem(
    val id: String = "",
    val productId: String = "",
    val variantId: String = "",
    val quantity: Int = 1,
    val image: String = "",
    val selectorColor: String = "",
    val selectSize: String = "",
    val price: Double = 0.0,
)