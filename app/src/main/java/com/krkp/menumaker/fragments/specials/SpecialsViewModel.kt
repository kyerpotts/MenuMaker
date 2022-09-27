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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SpecialsViewModel(application: Application) : AndroidViewModel(application) {

    private val resRepo: RestaurantRepository
    private val cartRepo: CartRepository

    private val readSpecialsData: LiveData<List<Food>>

    init {
        val resDao = RestaurantDatabase.getInstance(application).restaurantDao
        val cartDao = CartDatabase.getInstance(application).cartDao
        resRepo = RestaurantRepository.getInstance(resDao)
        cartRepo = CartRepository.getInstance(cartDao)
        readSpecialsData = resRepo.readSpecials
    }

    // Function adds items selected from the Specials Recycler View to the cart database. Cart database then retrieves the items to populate it's recyclerView
    fun addToCart() {
        viewModelScope.launch(Dispatchers.IO) {

        }
    }
}