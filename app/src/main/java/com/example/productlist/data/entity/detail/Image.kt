package com.example.productlist.data.entity.detail

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("SizeCode")
    var sizeCode: String?,
    @SerializedName("ImageUrl")
    var image: String
)
