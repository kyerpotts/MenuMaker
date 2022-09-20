package com.krkp.menumaker.database.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.krkp.menumaker.database.entities.Food
import com.krkp.menumaker.database.entities.Orders
import com.krkp.menumaker.database.entities.Restaurants
import com.krkp.menumaker.database.entities.Users

data class UsersWithOrders(
    @Embedded val users: Users,
    @Relation(
        parentColumn = "username",
        entityColumn = "username"
    )
    val order: List<Orders>
)
