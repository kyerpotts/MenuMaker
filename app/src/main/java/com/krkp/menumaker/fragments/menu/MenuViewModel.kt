package com.krkp.menumaker.fragments.menu

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.krkp.menumaker.database.cartdb.CartDatabase
import com.krkp.menumaker.database.cartdb.CartRepository
import com.krkp.menumaker.database.entities.Food
import com.krkp.menumaker.database.entities.OrderItem
import com.krkp.menumaker.database.restaurantdb.RestaurantDatabase
import com.krkp.menumaker.database.restaurantdb.RestaurantRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MenuViewModel(application: Application): AndroidViewModel(application) {
    // Repos provide a clean API for the ViewModel to access respective databases
    private val resRepo: RestaurantRepository
    private val cartRepo: CartRepository

    // Provides LiveData retrieved from the database to be used in the RecyclerView
    private val readMenuData: LiveData<List<Food>>

    init {
        val resDao = RestaurantDatabase.getInstance(application).restaurantDao
        val cartDao = CartDatabase.getInstance(application).cartDao
        resRepo = RestaurantRepository.getInstance(resDao)
        cartRepo = CartRepository.getInstance(cartDao)
        readMenuData = resRepo.retrieveMenuFrom("TODO: Restaurant name must be retrieved from RecyclerViewHolder OnClick")
    }

    // Function adds items selected from the Specials Recycler View to the cart database. Cart database then retrieves the items to populate it's recyclerView
    fun addToCart(orderItem: OrderItem) {
        viewModelScope.launch(Dispatchers.IO) {
            cartRepo.addOrderItem(orderItem)
        }
    }
}