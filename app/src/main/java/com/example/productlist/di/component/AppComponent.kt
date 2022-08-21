package com.example.productlist.di.component

import com.example.productlist.App
import com.example.productlist.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        ActivityBuildersModule::class,
        ApplicationModule::class,
        NetworkModule::class,
        ApiModule::class,
        RepositoryModule::class,
        ViewModelModule::class,
        DatabaseModule::class,]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: App): Builder
        fun build(): AppComponent
    }
}