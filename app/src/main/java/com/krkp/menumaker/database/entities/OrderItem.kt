package com.krkp.menumaker.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class OrderItem(
    @PrimaryKey(autoGenerate = false) val foodName: String,
    val numItems: Int,
    val restaurantName: String,
    val imgRef: String,
    val price: Double,
    val description: String
)