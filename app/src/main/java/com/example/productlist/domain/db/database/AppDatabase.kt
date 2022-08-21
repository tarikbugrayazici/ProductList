package com.example.productlist.domain.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.productlist.domain.db.dao.ProductDao
import com.example.productlist.domain.db.model.ProductLocal

@Database(entities = [ProductLocal::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}