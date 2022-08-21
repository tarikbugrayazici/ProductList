package com.example.productlist.di.module

import com.example.productlist.data.service.ProductDetailService
import com.example.productlist.data.service.ProductService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ApiModule {

    @Provides
    internal fun provideProductApi(retrofit: Retrofit): ProductService =
        retrofit.create(ProductService::class.java)

    @Provides
    internal fun provideProductDetailApi(retrofit: Retrofit): ProductDetailService =
        retrofit.create(ProductDetailService::class.java)


}