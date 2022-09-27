package com.krkp.menumaker.database

import androidx.lifecycle.LiveData
import com.krkp.menumaker.database.entities.Food

class Repository(private val resDao: RestaurantDao, private val userDao: UserDao){
    val readSpecials: LiveData<List<Food>> = resDao.getSpecials()

}