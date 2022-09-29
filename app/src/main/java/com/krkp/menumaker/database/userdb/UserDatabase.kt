package com.krkp.menumaker.database.userdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.krkp.menumaker.database.entities.Users
import com.krkp.menumaker.database.entities.Orders

@Database(
    entities = [
        Users::class,
        Orders::class
    ],
    version = 4
)
abstract class UserDatabase : RoomDatabase(){
    abstract val userDao: UserDao

    companion object{
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getInstance(context: Context): UserDatabase {
            synchronized(this){
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "userDatabase"
                ).
                fallbackToDestructiveMigration().build().also {
                    INSTANCE = it
//                createFromAsset("userDatabase.db").build().also {
//                    INSTANCE = it
                }
            }
        }
    }
}

//.createFromAsset("restaurantDatabase.db")