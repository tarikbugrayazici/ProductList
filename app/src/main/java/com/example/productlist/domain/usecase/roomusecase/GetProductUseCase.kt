package com.example.productlist.domain.usecase.roomusecase

import com.example.productlist.domain.db.model.ProductLocal
import com.example.productlist.domain.repository.room.RoomRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductUseCase @Inject constructor(
    private val repository: RoomRepository
) {

    operator fun invoke(): List<ProductLocal> {
        return repository.getProducts()
    }

}