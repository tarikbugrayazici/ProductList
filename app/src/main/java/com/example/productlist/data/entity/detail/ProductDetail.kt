package com.example.productlist.data.entity.detail

import com.google.gson.annotations.SerializedName

data class ProductDetail(
    @SerializedName("Result")
    val result: ResultDetail
)


fun ProductDetail.toResultDetailUi(): ResultDetailUi {
    return ResultDetailUi(result = result)
}