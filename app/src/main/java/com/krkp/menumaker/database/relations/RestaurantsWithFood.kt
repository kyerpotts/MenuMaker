package com.krkp.menumaker.database.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.krkp.menumaker.database.entities.Food
import com.krkp.menumaker.database.entities.Restaurants

data class RestaurantsWithFood(
    @Embedded val restaurants: Restaurants,
    @Relation(
        parentColumn = "restaurantName",
        entityColumn = "restaurantName"
    )
    val food: List<Food>
)
