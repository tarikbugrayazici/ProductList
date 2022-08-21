package com.example.productlist.di.module

import com.example.productlist.App
import com.example.productlist.data.service.ProductDetailService
import com.example.productlist.data.service.ProductService
import com.example.productlist.domain.db.dao.ProductDao
import com.example.productlist.domain.repository.preferences.StateSharedPrefRepository
import com.example.productlist.domain.repository.preferences.StateSharedPrefRepositoryImpl
import com.example.productlist.domain.repository.product.ProductDetailRepository
import com.example.productlist.domain.repository.product.ProductDetailRepositoryImpl
import com.example.productlist.domain.repository.product.ProductRepository
import com.example.productlist.domain.repository.product.ProductRepositoryImpl
import com.example.productlist.domain.repository.room.RoomRepository
import com.example.productlist.domain.repository.room.RoomRepositoryImpl
import dagger.Module
import dagger.Provides


@Module
class RepositoryModule {

    @Provides
    internal fun provideProductRepository(
        productApi: ProductService
    ): ProductRepository {
        return ProductRepositoryImpl(productApi)
    }

    @Provides
    internal fun provideProductDetailRepository(productDetail: ProductDetailService):
            ProductDetailRepository {
        return ProductDetailRepositoryImpl(productDetail)
    }

    @Provides
    internal fun provideSaveStateSharedRepository(context: App): StateSharedPrefRepository {
        return StateSharedPrefRepositoryImpl(context)
    }

    @Provides
    internal fun provideRoomRepository(dao: ProductDao): RoomRepository {
        return RoomRepositoryImpl(dao)
    }
}