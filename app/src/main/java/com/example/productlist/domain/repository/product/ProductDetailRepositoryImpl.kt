package com.example.productlist.domain.repository.product

import com.example.productlist.data.entity.detail.ProductDetail
import com.example.productlist.data.service.ProductDetailService
import javax.inject.Inject

class ProductDetailRepositoryImpl @Inject
constructor(
    private val productDetailService: ProductDetailService
) : ProductDetailRepository {

    override suspend fun getProductionDetailData(productId: Int): ProductDetail {
        return productDetailService.getProductsDetail(productId)
    }

}