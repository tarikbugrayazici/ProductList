package com.example.productlist.domain.usecase.product

import com.example.productlist.data.Result
import com.example.productlist.data.entity.detail.ResultDetailUi
import com.example.productlist.data.entity.detail.toResultDetailUi
import com.example.productlist.domain.repository.product.ProductDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ProductDetailUseCase @Inject constructor(
    private val productDetailRepository: ProductDetailRepository
) {

    operator fun invoke(productId: Int): Flow<Result<ResultDetailUi>> = flow {
        try {
            val productions = productDetailRepository.getProductionDetailData(
                productId
            )
            val item = productions.toResultDetailUi()
            emit(Result.Success<ResultDetailUi>(item))
        } catch (e: HttpException) {
            emit(Result.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Result.Error("Couldn't reach server. Check your internet connection"))
        }
    }
}