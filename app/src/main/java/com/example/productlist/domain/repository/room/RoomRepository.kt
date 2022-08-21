package com.example.productlist.domain.repository.room

import com.example.productlist.domain.db.model.ProductLocal
import kotlinx.coroutines.flow.Flow

interface RoomRepository {

    fun getProducts(): List<ProductLocal>

    suspend fun insertProduct(productLocal: ProductLocal)

    suspend fun deleteProduct(id:Int)

}