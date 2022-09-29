package com.krkp.menumaker

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.krkp.menumaker.database.entities.Users
import com.krkp.menumaker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // Login details
    var loggedIn: Boolean = false
    var user: Users? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun isLoggedIn() : Boolean {
        if(user != null) {
            Log.i("Checking Login", "User has successfully logged in")
            loggedIn = true
        }
        return loggedIn
    }
}