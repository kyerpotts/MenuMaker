package com.krkp.menumaker.database.cartdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.krkp.menumaker.database.entities.OrderItem

@Database(
    entities = [
        OrderItem::class
    ],
    version = 1
)
abstract class CartDatabase : RoomDatabase() {
    abstract val cartDao: CartDao

    companion object {
        @Volatile
        private var INSTANCE: CartDatabase? = null

        fun getInstance(context: Context): CartDatabase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    CartDatabase::class.java,
                    "cartDatabase"
                ).build().also {
                    INSTANCE = it
                }
            }
        }
    }
}