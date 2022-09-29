package com.krkp.menumaker.database.userdb

import androidx.annotation.Nullable
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.krkp.menumaker.database.entities.Orders
import com.krkp.menumaker.database.entities.Users

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(users: Users)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(orders: Orders)

    @Query("SELECT * FROM Users")
    suspend fun getAllUsers(): List<Users>

    @Query("SELECT * FROM Users WHERE username = :username")
    fun getUser(username: String): LiveData<Users>

    @Query("SELECT * FROM Orders WHERE username = :username")
    fun getOrders(username: String): LiveData<List<Orders>>

}