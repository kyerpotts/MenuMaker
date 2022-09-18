package com.krkp.menumaker.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Food(
    @PrimaryKey val foodId: Int,
    val foodName: String,
    val restaurantName: String,
    val imgRef: String,
    val price: Double,
    val description: String
)
