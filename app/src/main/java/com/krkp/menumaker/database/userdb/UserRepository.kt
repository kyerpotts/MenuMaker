package com.krkp.menumaker.database.userdb

import androidx.lifecycle.LiveData
import com.krkp.menumaker.database.entities.Users
import com.krkp.menumaker.database.restaurantdb.RestaurantDao
import com.krkp.menumaker.database.restaurantdb.RestaurantRepository


/**
 * A simple [UserRepository] class based on recommended Android Architecture components
 * Utilizes the Singleton pattern to provide a single instance of a repository designed to wrap
 * around database interactions to provide a clean API to [ViewModel] components.
 */
class UserRepository private constructor(private val userDao: UserDao) {
    companion object {
        @Volatile
        private lateinit var INSTANCE: UserRepository

        /**
         * Returns a threadsafe singleton instance of a User Repository
         */
        fun getInstance(userDao: UserDao): UserRepository {
            synchronized(this) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE = UserRepository(userDao)
                }
                return INSTANCE
            }
        }
    }

    // Function to retrieve userData
    fun retrieveUserFrom(username: String) : LiveData<Users> {
        return userDao.getUser(username)
    }

    // Function to add userData
    suspend fun addUser(user: Users) {
        userDao.insertUser(user)
    }
}