package io.kess.ecommerce.model

data class ProductVariant(
    val id: String = "",
    val color: String = "",
    val imageUrl: String? = null,
    val isAvailable: Boolean = true,
    val size: String = "",
    val stock: Int = 0
)