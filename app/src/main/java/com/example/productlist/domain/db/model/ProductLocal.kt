package com.example.productlist.domain.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductLocal(
    val id: Int? = null,
    val displayName: String,
    val imageUrl: String,
    @PrimaryKey val productId: Int,
    val isFavorite: Boolean = false
)
