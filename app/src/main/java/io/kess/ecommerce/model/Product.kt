package io.kess.ecommerce.model

data class Product(
    val id: String = "",
    val image: String = "",
    val name: String = "",
    val categoryID: String = "",
    val price: Double = 0.0,
    val discountPercentage: Double? = null,
    val description: String = "",
    val variantID: String = ""
)

data class ProductVariant(
    val id: String = "",
    val color: String = "",
    val imageUrl: String?,
    val isAvailable: Boolean = true,
    val price: Double = 0.0,
    val size: String = "",
    val stock: Int = 0
)


//data class ProductWithCategory(val id: String, name: String)
