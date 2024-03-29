package com.krkp.menumaker.database.restaurantdb

import androidx.lifecycle.LiveData
import androidx.room.*
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
    fun getRestaurants(): LiveData<List<Restaurants>>

    @Query("SELECT * FROM Food")
    suspend fun getFood(): List<Food>

    @Query("SELECT * FROM Food WHERE restaurantName = :restaurant")
    fun getRestaurantMenu(restaurant: String): LiveData<List<Food>>

    @Query("SELECT * FROM Food ORDER BY RANDOM() LIMIT 10")
    fun getSpecials(): LiveData<List<Food>>

    @Query("DELETE FROM restaurants WHERE restaurantName = :restaurant")
    suspend fun deleteRestaurant(restaurant: String)

}