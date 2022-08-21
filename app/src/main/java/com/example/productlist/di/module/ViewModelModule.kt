package com.example.productlist.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.productlist.di.factory.ViewModelFactory
import com.example.productlist.di.viewmodelkey.ViewModelKey
import com.example.productlist.ui.viewmodel.DetailViewModel
import com.example.productlist.ui.viewmodel.MainViewModel
import com.example.productlist.ui.viewmodel.PreferencesViewModel
import com.example.productlist.ui.viewmodel.ProductViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun mainViewModel(mainViewModel: MainViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun detailViewModel(detailViewModel: DetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PreferencesViewModel::class)
    abstract fun preferencesViewModel(preferencesViewModel: PreferencesViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(ProductViewModel::class)
    abstract fun productionViewModel(productViewModel: ProductViewModel): ViewModel


}