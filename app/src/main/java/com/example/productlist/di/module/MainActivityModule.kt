package com.example.productlist.di.module

import com.example.productlist.ui.detail.DetailFragment
import com.example.productlist.ui.main.view.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {


    @ContributesAndroidInjector
    internal abstract fun mainFragment(): MainFragment

    @ContributesAndroidInjector
    internal abstract fun detailFragment(): DetailFragment
}
