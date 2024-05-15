package com.example.ghosthome.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ghosthome.db.User

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: User)
    @Query("SELECT * FROM user WHERE name = :name AND password = :password LIMIT 1")
    fun getUserByNameAndPassword(name: String, password: String): LiveData<User>
}
