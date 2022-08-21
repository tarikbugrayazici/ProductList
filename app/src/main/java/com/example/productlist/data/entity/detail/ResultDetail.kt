package com.example.productlist.data.entity.detail

import com.google.gson.annotations.SerializedName

data class ResultDetail(
    @SerializedName("ProductId")
    val productId: Int,
    @SerializedName("DisplayName")
    val displayName: String,
    @SerializedName("BrandName")
    val brandName: String,
    @SerializedName("ActualPriceText")
    val actualPriceText: String,
    @SerializedName("Description")
    val description: Description,
    @SerializedName("Images")
    val images: ArrayList<Images>,
    var isFav: Boolean = false


)

