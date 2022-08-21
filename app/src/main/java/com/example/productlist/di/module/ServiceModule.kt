package com.example.productlist.di.module

import com.example.productlist.data.service.ProductDetailService
import com.example.productlist.data.service.ProductService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create

@Module
class ServiceModule {

    @Provides
    fun provideProductService(retrofit: Retrofit): ProductService = retrofit.create()

    @Provides
    fun provideProductDetailService(retrofit: Retrofit): ProductDetailService = retrofit.create()

}