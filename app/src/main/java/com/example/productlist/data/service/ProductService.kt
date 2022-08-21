package com.example.productlist.data.service

import com.example.productlist.data.entity.Product
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductService {

    @GET("list")
    suspend fun getProducts(
        @Query("siralama") siralama: String,
        @Query("sayfa") sayfa: Int,
        @Query("categoryId") categoryId: Int,
        @Query("includeDocuments") includeDocuments: Boolean
    ): Product
}