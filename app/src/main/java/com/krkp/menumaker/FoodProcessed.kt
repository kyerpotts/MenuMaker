package com.krkp.menumaker

import com.google.android.material.imageview.ShapeableImageView

data class FoodProcessed(
    val foodId: Int,
    val foodName: String,
    val restaurantName: String,
    val imgRef: ShapeableImageView,
    val price: Double,
    val description: String
)
