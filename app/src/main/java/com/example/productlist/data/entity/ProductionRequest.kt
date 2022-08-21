package com.example.productlist.data.entity

import com.google.gson.annotations.SerializedName

data class ProductionRequest(
    @SerializedName("siralama")
    val order: String,
    @SerializedName("sayfa")
    val page: Int,
    @SerializedName("ProductId")
    val categoryId: Int,
    @SerializedName("ProductId")
    val includeDocuments: Boolean
)
