package io.kess.ecommerce.model

data class Product(
    val id: String = "",
    val image: String = "",
    val name: String = "",
    val categoryId: String = "",
    val price: Double = 0.0,
    val discountPercentage: Double? = null,
    val description: String = "",
    val createdAt: Long = System.currentTimeMillis()
//    val createdAt: Long = 0L
)

