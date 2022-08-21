package com.example.productlist.domain.repository.product

import com.example.productlist.data.entity.detail.ProductDetail

interface ProductDetailRepository {
    suspend fun getProductionDetailData(productId: Int): ProductDetail
}