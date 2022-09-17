package com.krkp.menumaker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.krkp.menumaker.database.entities.Food
import com.krkp.menumaker.database.entities.Restaurants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dao = RestaurantDatabase.getInstance(this).restaurantDao

        // Test data
        val restaurants = Restaurants("Test Restaurant", "123 Fake Street", "Makes food")
        val food = Food("Test Food", "Test Restaurant", "TestImg", 19.99, "Tastes Good")

        GlobalScope.launch(Dispatchers.IO) {
            dao.insertRestaurant(restaurants)
            dao.insertFood(food)
        }

    }
}