package com.example.productlist.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productlist.data.Result
import com.example.productlist.data.entity.detail.ResultDetail
import com.example.productlist.data.entity.detail.ResultDetailUi
import com.example.productlist.domain.usecase.product.ProductDetailUseCase
import com.example.productlist.domain.usecase.roomusecase.GetProductUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val productDetailUseCase: ProductDetailUseCase,
    private val getProductUseCase: GetProductUseCase
) : ViewModel() {

    private var _productDetail = MutableLiveData<ResultDetail>()
    val productDetail: LiveData<ResultDetail>
        get() = _productDetail

    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String>
        get() = _errorLiveData


    fun getProductDetail(productId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                productDetailUseCase.invoke(
                    productId
                ).collect {
                    handleData(it)
                }
            }
        }
    }

    private fun handleData(result: Result<ResultDetailUi>) {
        when (result) {
            is Result.Success -> {
                result.data.result.let {
                    val productDetail = it
                    val fromDb = getProductUseCase.invoke().map { it.productId }
                    productDetail.let { product ->
                        if (fromDb.contains(product.productId))
                            product.isFav = true
                    }
                    _productDetail.postValue(productDetail)
                }
            }
            is Result.Error -> {
                _errorLiveData.value = result.exception
            }
        }
    }
}