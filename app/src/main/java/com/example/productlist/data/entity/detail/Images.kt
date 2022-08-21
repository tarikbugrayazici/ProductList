package com.example.productlist.data.entity.detail

import com.google.gson.annotations.SerializedName

data class Images(

    @SerializedName("DisplayOrder")
    var displayOrder: Int?,
    @SerializedName("Images")
    var images: ArrayList<Image>

)