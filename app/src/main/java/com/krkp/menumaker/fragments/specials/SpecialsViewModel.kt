package com.krkp.menumaker.fragments.specials

import android.app.Application
import android.service.autofill.UserData
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.krkp.menumaker.database.*
import com.krkp.menumaker.database.entities.Food
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SpecialsViewModel(application: Application) : AndroidViewModel(application) {
    private val readSpecialsData: LiveData<List<Food>>
    private val repository: Repository

    init {
        val resDao = RestaurantDatabase.getInstance(application).restaurantDao
        val userDao = UserDatabase.getInstance(application).userDao
        repository = Repository(resDao, userDao)
        readSpecialsData = repository.readSpecials
    }

    // Function adds items selected from the Specials Recycler View to the cart database. Cart database then retrieves the items to populate it's recyclerView
    fun addToCart() {
        viewModelScope.launch(Dispatchers.IO) {
            TODO("Create cart table for users Database along with CartFoodItem, cartFoodItem must have an integer value and a user assigned to it")

        }
    }
}