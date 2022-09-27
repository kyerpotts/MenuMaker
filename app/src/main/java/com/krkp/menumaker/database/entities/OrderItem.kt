package com.krkp.menumaker.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class OrderItem(
    @PrimaryKey(autoGenerate = false) val orderItemId: Int,
    val numItems: Int,
    val foodName: String,
    val restaurantName: String,
    val imgRef: String,
    val price: Double,
    val description: String
)