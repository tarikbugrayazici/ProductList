package com.example.productlist.di.module

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    @Named(BASE_URL)
    fun provideBaseUrl(): String = "https://www.beymen.com/Mobile2/api/mbproduct/"

    @Provides
    internal fun provideRetrofit(
        @Named(BASE_URL) baseUrl: String,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(gsonConverterFactory)
            .build()

    @Provides
    internal fun provideGson(): GsonConverterFactory = GsonConverterFactory.create()


    companion object {
        private const val BASE_URL = "base_url"
    }

}