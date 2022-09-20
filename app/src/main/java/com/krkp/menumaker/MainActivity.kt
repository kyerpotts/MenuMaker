package com.krkp.menumaker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.krkp.menumaker.database.entities.Food
import com.krkp.menumaker.database.entities.Orders
import com.krkp.menumaker.database.entities.Restaurants
import com.krkp.menumaker.database.entities.Users
import com.krkp.menumaker.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val rdao = RestaurantDatabase.getInstance(this).restaurantDao
        val udao = UserDatabase.getInstance(this).userDao
        //var specialsList : List<Food> = emptyList()


        lifecycleScope.launch {
            //specialsList = dao.getSpecials()

            rdao.getRestaurants()
            udao.getAllUsers()
        }


        /*
        // sets up recyclerview
        val adapter = SpecialsAdapter(specialsList)
        binding.rvSpecials.adapter = adapter
        binding.rvSpecials.layoutManager = LinearLayoutManager(this)

        //button to move to next activity
        binding.btnToRestaurants.setOnClickListener {
            Intent(this,MenuActivity::class.java).also {
                startActivity(it)
            }
        }

         */
    }
}