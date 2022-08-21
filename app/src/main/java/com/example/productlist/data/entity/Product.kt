package com.example.productlist.data.entity

import com.google.gson.annotations.SerializedName

data class Product(

    @SerializedName("Result")
    val result: Result

)

fun Product.toProductUi(): ProductUi {
    return ProductUi(result = result)
}