package com.krkp.menumaker.fragments.restaurants

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.krkp.menumaker.database.cartdb.CartDatabase
import com.krkp.menumaker.database.cartdb.CartRepository
import com.krkp.menumaker.database.entities.OrderItem
import com.krkp.menumaker.database.entities.Restaurants
import com.krkp.menumaker.database.restaurantdb.RestaurantDatabase
import com.krkp.menumaker.database.restaurantdb.RestaurantRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RestaurantsViewModel(application: Application) : AndroidViewModel(application) {
    // Repos provide a clean API for the ViewModel to access respective databases
    private val resRepo: RestaurantRepository
    private val cartRepo: CartRepository

    // Provides LiveData retrieved from the database to be used in the RecyclerView
    private val readRestaurantsData: LiveData<List<Restaurants>>

    init {
        val resDao = RestaurantDatabase.getInstance(application).restaurantDao
        val cartDao = CartDatabase.getInstance(application).cartDao
        resRepo = RestaurantRepository.getInstance(resDao)
        cartRepo = CartRepository.getInstance(cartDao)
        readRestaurantsData = resRepo.readRestaurants
    }


    // Function retrieves Restaurants data
    fun retrieveRestaurantsData(): LiveData<List<Restaurants>> {
        return this.readRestaurantsData
    }

    // Function inserts Restaurants data
    fun insertRestaurant(restaurants: Restaurants) {
        viewModelScope.launch(Dispatchers.IO) {
            resRepo.insertRestaurant(restaurants)
        }
    }

    // Function deletes Restaurants data
    fun deleteRestaurant(restaurant: String) {
        viewModelScope.launch(Dispatchers.IO) {
            resRepo.deleteRestaurant(restaurant)
        }
    }

}