package com.krkp.menumaker.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Users (
    @PrimaryKey(autoGenerate = false) val username: String,
    val password: String
)