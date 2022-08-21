package com.example.productlist.domain.db.dao

import androidx.room.*
import com.example.productlist.domain.db.model.ProductLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Query("SELECT *  FROM productlocal")
    fun getAll(): List<ProductLocal>

    @Query("DELETE FROM productlocal WHERE productId = :id")
    suspend fun deleteProduct(id: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(products: ProductLocal)
}