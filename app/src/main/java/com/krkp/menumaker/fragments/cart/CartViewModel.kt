package com.krkp.menumaker.fragments.cart

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.krkp.menumaker.database.cartdb.CartDatabase
import com.krkp.menumaker.database.cartdb.CartRepository
import com.krkp.menumaker.database.entities.Food
import com.krkp.menumaker.database.entities.OrderItem
import com.krkp.menumaker.database.entities.Orders
import com.krkp.menumaker.database.restaurantdb.RestaurantRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CartViewModel(application: Application) : AndroidViewModel(application) {

    // Repos provide a clean API for the ViewModel to access respective databases
    private val cartRepo: CartRepository

    // Provides LiveData retrieved from the database to be used in the RecyclerView
    private val readCartData: LiveData<List<OrderItem>>

    init {
        val cartDao = CartDatabase.getInstance(application).cartDao
        cartRepo = CartRepository.getInstance(cartDao)
        readCartData = cartRepo.readCart
    }

    // Function retrieves cart data
    fun retrieveCartData(): LiveData<List<OrderItem>> {
        return this.readCartData
    }

    // Function removes items from the cart database
    fun removeFromCart(orderItem: OrderItem) {
        viewModelScope.launch(Dispatchers.IO) {
            cartRepo.removeOrderItem(orderItem)
        }
    }

    // Function updates cart data in the database
    fun updateCartData(orderItem: OrderItem) {
        viewModelScope.launch(Dispatchers.IO) {
            cartRepo.updateOrderItem(orderItem)
        }
    }

    /**
     * Function to determine whether to update existing database entry or remove it based on the
     * value of numItems in the OrderItem Object.
     * A numItems value of 0 will be removed from the database as it no longer needs to be displayed
     * in the cart. A value of 1 or high will be updated as the item will persist in the cart
     */
    fun updateOrRemoveFromCart(orderItem: OrderItem) {
        if (orderItem.numItems > 0) {
            updateCartData(orderItem)
        } else {
            removeFromCart(orderItem)
        }
    }

//    fun convertToOrder(orderItems: List<OrderItem>): Orders {
//        var orderString: String
//        for (i in orderItems) {
//          orderString = i.numItems.toString() + " " +
//        }
//        val order: Orders = Orders(0, orderItems[0].)
//
//        return order
//    }

}