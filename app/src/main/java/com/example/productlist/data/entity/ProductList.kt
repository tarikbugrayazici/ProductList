package com.example.productlist.data.entity

import com.google.gson.annotations.SerializedName

data class ProductList(
    @SerializedName("ProductId")
    val productId: Int,
    @SerializedName("DisplayName")
    val displayName: String,
    @SerializedName("ImageUrl")
    val imageUrl: String,
    @SerializedName("CdnResizedImageUrl")
    val cdnResizedImageUrl: String,
    var isFav: Boolean = false
)
