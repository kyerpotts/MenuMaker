package com.krkp.menumaker.fragments.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.krkp.menumaker.database.cartdb.CartRepository
import com.krkp.menumaker.database.entities.OrderItem
import com.krkp.menumaker.database.entities.Users
import com.krkp.menumaker.database.restaurantdb.RestaurantRepository
import com.krkp.menumaker.database.userdb.UserDatabase
import com.krkp.menumaker.database.userdb.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    // Repos provide a clean API for the ViewModel to access respective databases
    private val userRepo: UserRepository

    // Provides LiveData retrieved from the database to be used for login purposes

    init {
        val userDao = UserDatabase.getInstance(application).userDao
        userRepo = UserRepository.getInstance(userDao)
    }

    // Function retrieves Users LiveData to be used for login functionality
    fun retrieveUserData(username: String): LiveData<Users> {
        return userRepo.retrieveUserFrom(username)
    }

    fun isValidUser(user: Users?, username: String, password: String): Boolean {
        if (user != null) {
            if (user.username == username && user.password == password) {
                return true
            }
        }
        return false
    }

    fun registerUser(user: Users) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepo.addUser(user)
        }
    }
}