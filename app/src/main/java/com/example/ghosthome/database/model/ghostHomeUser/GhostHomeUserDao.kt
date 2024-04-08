package com.example.ghosthome.database.model.ghostHomeUser

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ghosthome.database.model.ghostHomeUser.GhostHomeUser

@Dao
abstract class GhostHomeUserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertGhostHomeUser(ghostHomeUser: GhostHomeUser)

    @Query(
        "SELECT * FROM  ghost_home_user_table"
    )
    abstract fun getAllGhostHomeUser() : GhostHomeUser?
}