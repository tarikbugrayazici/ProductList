package com.example.productlist.domain.repository.product

import com.example.productlist.data.entity.Product
import com.example.productlist.data.entity.ProductionRequest
import com.example.productlist.data.service.ProductService
import com.example.productlist.domain.repository.product.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productApi: ProductService
) : ProductRepository {

    override suspend fun getProductionData(productionRequest: ProductionRequest): Product {
        return productApi.getProducts(
            productionRequest.order,
            productionRequest.page,
            productionRequest.categoryId,
            productionRequest.includeDocuments
        )
    }

}