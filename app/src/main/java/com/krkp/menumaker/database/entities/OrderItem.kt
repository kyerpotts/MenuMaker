package com.krkp.menumaker.database.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class OrderItem(
    @PrimaryKey(autoGenerate = false) val foodName: String,
    val numItems: Int,
    val restaurantName: String,
    val imgRef: String,
    val price: Double,
    val description: String
) : Parcelable