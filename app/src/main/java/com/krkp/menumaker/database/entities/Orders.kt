package com.krkp.menumaker.database.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Orders (
    @PrimaryKey(autoGenerate = true) val orderID: Int,
    val dateTime: String,
    val username: String,
    val order: String,
    val total: Double
): Parcelable