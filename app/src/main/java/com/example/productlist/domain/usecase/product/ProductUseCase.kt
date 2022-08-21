package com.example.productlist.domain.usecase.product

import com.example.productlist.data.Result
import com.example.productlist.data.entity.ProductUi
import com.example.productlist.data.entity.ProductionRequest
import com.example.productlist.data.entity.toProductUi
import com.example.productlist.domain.repository.product.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ProductUseCase @Inject constructor(private val productRepository: ProductRepository) {

    operator fun invoke(productionRequest: ProductionRequest):
            Flow<Result<ProductUi>> = flow {
        try {
            val productions = productRepository.getProductionData(
                productionRequest
            )
            val item = productions.toProductUi()
            emit(Result.Success<ProductUi>(item))
        } catch (e: HttpException) {
            emit(Result.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Result.Error("Couldn't reach server. Check your internet connection"))
        }
    }

}