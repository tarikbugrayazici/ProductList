package com.example.productlist.di.module

import com.example.productlist.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [MainActivityModule::class] )
    internal abstract fun mainActivity(): MainActivity
}