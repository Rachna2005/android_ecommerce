package io.kess.ecommerce.model

data class Product(
    val id: String,
    val image: String,
    val name: String,
    val categoryID: String,
    val price: Double,
    val discountPercentage: Double? = null,
    val description: String
)

data class ProductVariant(
    val color: String,
    val imageUrl: String?,
    val isAvailable: Boolean,
    val price: Double,
    val size: String,
    val stock: Int
)

data class ProductWithCategory(val id: String, name: String)
