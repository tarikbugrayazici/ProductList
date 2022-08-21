package com.example.productlist.domain.usecase.roomusecase

import com.example.productlist.domain.repository.room.RoomRepository
import javax.inject.Inject

class DeleteProductUseCase @Inject constructor(
    private val repository: RoomRepository
) {

    suspend operator fun invoke(productId: Int) {
        repository.deleteProduct(productId)
    }
}