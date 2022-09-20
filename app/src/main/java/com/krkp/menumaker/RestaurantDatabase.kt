package com.krkp.menumaker

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.krkp.menumaker.database.RestaurantDao
import com.krkp.menumaker.database.entities.Restaurants
import com.krkp.menumaker.database.entities.Food

@Database(
    entities = [
        Restaurants::class,
        Food::class
    ],
    version = 1
)
abstract class RestaurantDatabase : RoomDatabase(){
    abstract val restaurantDao: RestaurantDao

    companion object{
        @Volatile
        private var INSTANCE: RestaurantDatabase? = null

        fun getInstance(context: Context): RestaurantDatabase{
            synchronized(this){
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    RestaurantDatabase::class.java,
                    "restaurantDatabase"
                ).createFromAsset("restaurantDatabase.db").build().also {
                    INSTANCE = it
                }
            }
        }
    }
}

//.createFromAsset("restaurantDatabase.db")