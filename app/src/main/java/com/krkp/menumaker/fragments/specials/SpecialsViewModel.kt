package com.krkp.menumaker.fragments.specials

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.krkp.menumaker.database.cartdb.CartDatabase
import com.krkp.menumaker.database.cartdb.CartRepository
import com.krkp.menumaker.database.restaurantdb.RestaurantDatabase
import com.krkp.menumaker.database.restaurantdb.RestaurantRepository
import com.krkp.menumaker.database.entities.Food
import com.krkp.menumaker.database.entities.OrderItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SpecialsViewModel(application: Application) : AndroidViewModel(application) {

    // Repos provide a clean API for the ViewModel to access respective databases
    private val resRepo: RestaurantRepository
    private val cartRepo: CartRepository

    // Provides LiveData retrieved from the database to be used in the RecyclerView
    private val readSpecialsData: LiveData<List<Food>>

    init {
        val resDao = RestaurantDatabase.getInstance(application).restaurantDao
        val cartDao = CartDatabase.getInstance(application).cartDao
        resRepo = RestaurantRepository.getInstance(resDao)
        cartRepo = CartRepository.getInstance(cartDao)
        readSpecialsData = resRepo.readSpecials
    }

    // Function adds items selected from the Specials Recycler View to the cart database. Cart database then retrieves the items to populate it's recyclerView
    fun addToCart(orderItem: OrderItem) {
        viewModelScope.launch(Dispatchers.IO) {
            if (orderItem.numItems < 1) {
                val newOrderItem = OrderItem(
                    orderItem.foodName,
                    1,
                    orderItem.restaurantName,
                    orderItem.imgRef,
                    orderItem.price,
                    orderItem.description
                )
                cartRepo.addOrderItem(newOrderItem)
            }
            else {
                cartRepo.addOrderItem(orderItem)
            }
        }
    }

    // Function retrieves Specials data
    fun retrieveSpecialsData(): LiveData<List<Food>> {
        return this.readSpecialsData
    }
}