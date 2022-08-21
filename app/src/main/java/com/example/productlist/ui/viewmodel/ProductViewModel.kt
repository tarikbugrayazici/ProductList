package com.example.productlist.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productlist.data.entity.ProductList
import com.example.productlist.data.entity.detail.ResultDetail
import com.example.productlist.domain.db.model.ProductLocal
import com.example.productlist.domain.usecase.roomusecase.DeleteProductUseCase
import com.example.productlist.domain.usecase.roomusecase.InsertProductUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductViewModel @Inject constructor(
    private val deleteProductUseCase: DeleteProductUseCase,
    private val insertProductUseCase: InsertProductUseCase
) : ViewModel() {


    suspend fun favClicked(product: ProductList) {
        val productLocal = ProductLocal(
            displayName = product.displayName,
            imageUrl = product.imageUrl,
            productId = product.productId
        )
        viewModelScope.launch {
            if (product.isFav) insertProductUseCase.invoke(productLocal)
            else deleteProductUseCase.invoke(product.productId)
        }
    }

    suspend fun favClickedFromDetail(product: ResultDetail) {
        val productLocal = ProductLocal(
            displayName = product.displayName,
            imageUrl = product.images[1].images[0].image,
            productId = product.productId
        )
        viewModelScope.launch {
            if (product.isFav)
                insertProductUseCase.invoke(productLocal)

            else
                product.productId.let { deleteProductUseCase.invoke(it) }
        }
    }
}