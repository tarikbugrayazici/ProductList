package com.example.productlist.domain.repository.room

import com.example.productlist.domain.db.dao.ProductDao
import com.example.productlist.domain.db.model.ProductLocal
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoomRepositoryImpl @Inject constructor(private val dao: ProductDao) : RoomRepository {
    override fun getProducts(): List<ProductLocal> {
        return dao.getAll()
    }

    override suspend fun insertProduct(productLocal: ProductLocal) {
        return dao.insertProduct(productLocal)
    }

    override suspend fun deleteProduct(id: Int) {
        return dao.deleteProduct(id)
    }
}