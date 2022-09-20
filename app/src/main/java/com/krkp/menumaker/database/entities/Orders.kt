package com.krkp.menumaker.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Orders (
    @PrimaryKey(autoGenerate = false) val orderID: Int,
    val username: String,
    val order: String
)