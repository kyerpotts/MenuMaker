package com.krkp.menumaker.database.restaurantdb

import androidx.lifecycle.LiveData
import com.krkp.menumaker.database.entities.Food

/**
 * A simple [RestaurantRepository] class based on recommended Android Architecture components
 * Utilizes the Singleton pattern to provide a single instance of a repository designed to wrap
 * around database interactions to provide a clean API to [ViewModel] components.
 */
class RestaurantRepository private constructor(resDao: RestaurantDao) {
    companion object {
        @Volatile
        private lateinit var INSTANCE: RestaurantRepository

        /**
         * Returns a threadsafe singleton instance of a Restaurant Repository
         */
        fun getInstance(resDao: RestaurantDao): RestaurantRepository {
            synchronized(this) {
                if(!::INSTANCE.isInitialized) {
                    INSTANCE = RestaurantRepository(resDao)
                }
                return INSTANCE
            }
        }
    }

    val readSpecials: LiveData<List<Food>> = resDao.getSpecials()
//    val readCart: LiveData<List<OrderItem>> = cartDao.getCartList()

}