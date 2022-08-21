package com.example.productlist.data.service

import com.example.productlist.data.entity.detail.ProductDetail
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductDetailService {

    @GET("product")
    suspend fun getProductsDetail(
        @Query("productid") productId: Int,
    ): ProductDetail
}