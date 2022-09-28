package com.krkp.menumaker.database.restaurantdb

import androidx.lifecycle.LiveData
import com.krkp.menumaker.database.entities.Food
import com.krkp.menumaker.database.entities.Restaurants

/**
 * A simple [RestaurantRepository] class based on recommended Android Architecture components
 * Utilizes the Singleton pattern to provide a single instance of a repository designed to wrap
 * around database interactions to provide a clean API to [ViewModel] components.
 */
class RestaurantRepository private constructor(private val resDao: RestaurantDao) {
    companion object {
        @Volatile
        private lateinit var INSTANCE: RestaurantRepository

        /**
         * Returns a threadsafe singleton instance of a Restaurant Repository
         */
        fun getInstance(resDao: RestaurantDao): RestaurantRepository {
            synchronized(this) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE = RestaurantRepository(resDao)
                }
                return INSTANCE
            }
        }
    }

    // LiveData objects to be utilised in ViewModels and RecyclerViews
    val readSpecials: LiveData<List<Food>> = resDao.getSpecials()
    val readRestaurants: LiveData<List<Restaurants>> = resDao.getRestaurants()

    // Function to retrieve Menu of a given Restaurant
    fun retrieveMenuFrom(restaurantName: String) : LiveData<List<Food>> {
        return resDao.getRestaurantMenu(restaurantName)
    }

    suspend fun insertRestaurant(restaurants: Restaurants) {
        resDao.insertRestaurant(restaurants)
    }

    suspend fun deleteRestaurant(restaurant: String) {
        resDao.deleteRestaurant(restaurant)
    }
}