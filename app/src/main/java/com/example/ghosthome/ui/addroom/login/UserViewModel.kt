package com.example.ghosthome.ui.addroom.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ghosthome.db.User
import com.example.ghosthome.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun insertUser(name: String, password: String, phoneNumber: String) {
        val user = User(name = name, password = password, phoneNumber = phoneNumber)
        viewModelScope.launch {
            userRepository.insertUser(user)
        }
    }

    fun getUserByNameAndPassword(name: String, password: String): LiveData<User> {
        return userRepository.getUserByNameAndPassword(name, password)
    }
}
