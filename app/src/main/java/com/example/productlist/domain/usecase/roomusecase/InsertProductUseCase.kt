package com.example.productlist.domain.usecase.roomusecase

import com.example.productlist.domain.db.model.ProductLocal
import com.example.productlist.domain.repository.room.RoomRepository
import javax.inject.Inject

class InsertProductUseCase @Inject constructor(
    private val repository: RoomRepository
) {

    suspend operator fun invoke(productLocal: ProductLocal) {
            repository.insertProduct(productLocal)
    }
}