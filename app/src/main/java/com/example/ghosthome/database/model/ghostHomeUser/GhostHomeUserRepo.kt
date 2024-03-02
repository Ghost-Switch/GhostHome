package com.example.ghosthome.database.model.ghostHomeUser

import android.content.Context
import com.example.ghosthome.database.DatabaseGhostHome
import com.example.ghosthome.database.model.ghostHomeUser.GhostHomeUser

class GhostHomeUserRepo(val context: Context) {
    private val database = DatabaseGhostHome.getInstance(context)
    private val ghostHomeUserDao = database.ghostHomeUserDao()


    fun insertGhostHomeUser(ghostHomeUser: GhostHomeUser){
        ghostHomeUserDao.insertGhostHomeUser(ghostHomeUser)
    }
}