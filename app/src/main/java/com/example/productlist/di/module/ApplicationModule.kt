package com.example.productlist.di.module

import android.content.Context
import com.example.productlist.App
import dagger.Binds
import dagger.Module

@Module
abstract class ApplicationModule {

    @Binds
    abstract fun bindContext(androidApplication: App): Context


}