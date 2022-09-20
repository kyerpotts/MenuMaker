package com.krkp.menumaker.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.krkp.menumaker.database.entities.Food
import com.krkp.menumaker.database.entities.Orders
import com.krkp.menumaker.database.entities.Restaurants
import com.krkp.menumaker.database.entities.Users

@Dao
interface RestaurantDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRestaurant(restaurants: Restaurants)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFood(food: Food)

    @Query("SELECT * FROM Restaurants")
    suspend fun getRestaurants(): List<Restaurants>

    @Query("SELECT * FROM Food")
    suspend fun getFood(): List<Food>

    @Query("SELECT * FROM Food WHERE restaurantName = :restaurant")
    suspend fun getRestaurantMenu(restaurant: String): List<Food>

    @Query("SELECT * FROM Food ORDER BY RANDOM() LIMIT 10")
    suspend fun getSpecials(): List<Food>

}