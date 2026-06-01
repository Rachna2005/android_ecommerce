package io.kess.ecommerce.model

import com.google.firebase.Timestamp

data class Order(
    val id: String = "",
    val totalPrice: Double = 0.0,
    val itemsL: List<CartItem> = emptyList(),
    val status: String = "PENDING",
    val address: String = "",
    val phoneNumber: String = "",
    val createdAt: Timestamp? = null
)