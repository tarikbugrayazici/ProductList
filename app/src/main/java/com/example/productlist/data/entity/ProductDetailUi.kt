package com.example.productlist.data.entity

data class ProductDetailUi(
    var productId: Int,
    var displayName: String,
    var imageUrl: String,
    var cdnResizedImageUrl: String,
    var isFavorite: Boolean = false
)
