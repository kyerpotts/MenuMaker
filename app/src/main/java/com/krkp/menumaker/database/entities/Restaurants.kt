package com.krkp.menumaker.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Restaurants (
    @PrimaryKey(autoGenerate = false) val restaurantName: String,
    val address: String,
    val logo: String,
    val bio: String
    )