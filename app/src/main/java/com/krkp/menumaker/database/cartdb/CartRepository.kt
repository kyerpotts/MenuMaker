package com.krkp.menumaker.database.cartdb

import androidx.lifecycle.LiveData
import com.krkp.menumaker.database.entities.OrderItem
import com.krkp.menumaker.database.restaurantdb.RestaurantDao
import com.krkp.menumaker.database.restaurantdb.RestaurantRepository


/**
 * A simple [CartRepository] class based on recommended Android Architecture components
 * Utilizes the Singleton pattern to provide a single instance of a repository designed to wrap
 * around database interactions to provide a clean API to [ViewModel] components.
 */
class CartRepository private constructor(private val cartDao: CartDao) {
    companion object {
        @Volatile
        private lateinit var INSTANCE: CartRepository

        /**
         * Returns a threadsafe singleton instance of a Cart Repository
         */
        fun getInstance(cartDao: CartDao): CartRepository {
            synchronized(this) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE = CartRepository(cartDao)
                }
                return INSTANCE
            }
        }
    }

    // LiveData objects to be utilised in ViewModels
    val readCart: LiveData<List<OrderItem>> = cartDao.getCart()

    suspend fun addOrderItem(orderItem: OrderItem) {
        cartDao.insertOrderItem(orderItem)
    }

    suspend fun removeOrderItem(orderItem: OrderItem) {
        cartDao.deleteOrderItem(orderItem)
    }

    suspend fun updateOrderItem(orderItem: OrderItem) {
        cartDao.updateOrderItem(orderItem)
    }

    suspend fun clearAllFromCart() {
        cartDao.clearCart()
    }

}