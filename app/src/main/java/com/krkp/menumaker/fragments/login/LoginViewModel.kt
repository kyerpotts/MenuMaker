package com.krkp.menumaker.fragments.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.krkp.menumaker.database.cartdb.CartDatabase
import com.krkp.menumaker.database.cartdb.CartRepository
import com.krkp.menumaker.database.entities.OrderItem
import com.krkp.menumaker.database.entities.Orders
import com.krkp.menumaker.database.entities.Users
import com.krkp.menumaker.database.userdb.UserDatabase
import com.krkp.menumaker.database.userdb.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    // Repos provide a clean API for the ViewModel to access respective databases
    private val userRepo: UserRepository
    private val cartRepo: CartRepository

    // Provides LiveData retrieved from the database to be used for login purposes

    init {
        val userDao = UserDatabase.getInstance(application).userDao
        val cartDao = CartDatabase.getInstance(application).cartDao
        userRepo = UserRepository.getInstance(userDao)
        cartRepo = CartRepository.getInstance(cartDao)
    }

    // Function retrieves Users LiveData to be used for login functionality
    fun retrieveUserData(username: String): LiveData<Users> {
        return userRepo.retrieveUserFrom(username)
    }

    // Function retrieves Users LiveData to be used for login functionality
    fun retrieveOrdersData(username: String): LiveData<List<Orders>> {
        return userRepo.retrieveOrdersFrom(username)
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

    fun dispatchOrder(username: String, orderList: Array<OrderItem>) {
        if (orderList.isNotEmpty()) {
            viewModelScope.launch(Dispatchers.IO) {
                var totalPrice: Double = 0.0
                var orderString: String = ""
                for (i in orderList) {
                    orderString += buildString {
                        append(i.numItems)
                        append("   ")
                        append(i.restaurantName)
                        append(" - ")
                        append(i.foodName)
                        append(" : ")
                        append("$")
                        append(String.format("%.2f", i.price))
                        append("\n")
                    }
                    totalPrice += i.price
                }
                val newOrder =
                    Orders(0, LocalDateTime.now().toString(), username, orderString, totalPrice)
                userRepo.sendOrder(newOrder)
                cartRepo.clearAllFromCart()
            }
        }
    }
}