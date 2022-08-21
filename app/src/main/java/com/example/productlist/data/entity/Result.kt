package com.example.productlist.data.entity

import com.google.gson.annotations.SerializedName


data class Result(
    @SerializedName("ProductList")
    val productList: List<ProductList>
)
