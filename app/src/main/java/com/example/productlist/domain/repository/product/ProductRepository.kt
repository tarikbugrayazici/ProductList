package com.example.productlist.domain.repository.product

import com.example.productlist.data.entity.Product
import com.example.productlist.data.entity.ProductionRequest

interface ProductRepository {

    suspend fun getProductionData(productionRequest: ProductionRequest): Product

}