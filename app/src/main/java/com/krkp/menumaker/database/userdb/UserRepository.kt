package com.krkp.menumaker.database.userdb

import com.krkp.menumaker.database.restaurantdb.RestaurantDao
import com.krkp.menumaker.database.restaurantdb.RestaurantRepository


/**
 * A simple [UserRepository] class based on recommended Android Architecture components
 * Utilizes the Singleton pattern to provide a single instance of a repository designed to wrap
 * around database interactions to provide a clean API to [ViewModel] components.
 */
class UserRepository private constructor(userDao: UserDao) {
    companion object {
        @Volatile
        private lateinit var INSTANCE: UserRepository

        /**
         * Returns a threadsafe singleton instance of a Restaurant Repository
         */
        fun getInstance(userDao: UserDao): UserRepository {
            synchronized(this) {
                if(!::INSTANCE.isInitialized) {
                    INSTANCE = UserRepository(userDao)
                }
                return INSTANCE
            }
        }
    }
}