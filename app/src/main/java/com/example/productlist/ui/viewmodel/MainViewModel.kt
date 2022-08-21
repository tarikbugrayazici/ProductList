package com.example.productlist.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productlist.data.Result
import com.example.productlist.data.entity.ProductUi
import com.example.productlist.data.entity.ProductionRequest
import com.example.productlist.domain.db.dao.ProductDao
import com.example.productlist.domain.usecase.product.ProductUseCase
import com.example.productlist.domain.usecase.roomusecase.GetProductUseCase
import com.example.productlist.util.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val productUseCase: ProductUseCase,
    private val getProductUseCase: GetProductUseCase
) : ViewModel() {

    private var page: Int = 1
    private var productSize: Int = 0
    private var isFetchingData: Boolean = false

    private var _products = MutableLiveData<ProductUi>()
    val products: LiveData<ProductUi>
        get() = _products

    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String>
        get() = _errorLiveData

    private val _addListLiveData = MutableLiveData<ProductUi>()
    val addListLiveData: LiveData<ProductUi>
        get() = _addListLiveData


    fun getProducts() {
        if (isFetchingData) return
        isFetchingData = true
        viewModelScope.launch(Dispatchers.IO) {
            val productionUi = ProductionRequest(
                order = Constants.SIRALAMA,
                page = page,
                categoryId = Constants.CATEGORYID,
                includeDocuments = Constants.INCLUDEDOCUMENTS
            )
            productUseCase.invoke(
                productionUi
            ).collect {
                handleData(it)
            }
        }
    }

    fun paginateCollection(lastVisibleItemPosition: Int) {
        if (lastVisibleItemPosition < productSize - PAGINATION_THRESHOLD) {
            return
        } else {
            getProducts()
        }
    }

    private fun handleData(result: Result<ProductUi>) {
        when (result) {
            is Result.Success -> {
                result.data.let {
                    val productList = it
                    val fromDb = getProductUseCase.invoke().map { it.productId }
                    productList.result.productList.filter { product ->
                        fromDb.contains(product.productId)
                    }.map { it.isFav = true }
                    productSize += productList.result.productList.size
                    if (page == 1) {
                        _products.postValue(productList)
                    } else {
                        _addListLiveData.postValue(productList)
                    }
                    if (it.result.productList.isNotEmpty()) page++
                    isFetchingData = false
                }
            }
            is Result.Error -> {
                if (page == 0) _errorLiveData.value = result.exception
            }
        }
    }

    fun clearData() {
        page = 1
        productSize = 0
    }

    companion object {
        private const val PAGINATION_THRESHOLD = 46
    }
}