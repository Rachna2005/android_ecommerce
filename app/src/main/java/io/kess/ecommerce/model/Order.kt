package io.kess.ecommerce.model

data class Order(
    val id: String = "",
    val totalPrice: Double = 0.0,
    val status: String = "PENDING",
    val address: ShippingAddress = ShippingAddress()
)