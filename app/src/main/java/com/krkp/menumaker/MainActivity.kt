package com.krkp.menumaker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dao = RestaurantDatabase.getInstance(this).restaurantDao

        // needs action to put it into memory
        lifecycleScope.launch {
            dao.getRestaurants()
        }
    }
}