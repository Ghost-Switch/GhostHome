package com.example.ghosthome.repository

import androidx.lifecycle.LiveData
import com.example.ghosthome.dao.UserDao
import com.example.ghosthome.db.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(private val userDao: UserDao) {

    suspend fun insertUser(user: User) {
        withContext(Dispatchers.IO) {
            userDao.insert(user)
        }
    }

    fun getUserByNameAndPassword(name: String, password: String): LiveData<User> {
        return userDao.getUserByNameAndPassword(name, password)
    }
}
