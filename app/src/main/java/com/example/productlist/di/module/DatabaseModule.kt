package com.example.productlist.di.module

import android.content.Context
import androidx.room.Room
import com.example.productlist.domain.db.dao.ProductDao
import com.example.productlist.domain.db.database.AppDatabase
import com.example.productlist.util.Constants
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
 class DatabaseModule {

    @Provides
    @Singleton
     fun provideProductionDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            Constants.DATABASE_NAME
        ).build()
    }

    @Provides
    fun providesProductDao(appDatabase: AppDatabase): ProductDao {
        return appDatabase.productDao()
    }
}