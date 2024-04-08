package com.example.ghosthome.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.ghosthome.database.model.ghostHomeUser.GhostHomeUser
import com.example.ghosthome.database.model.ghostHomeUser.GhostHomeUserDao

@Database(
    entities = [
        GhostHomeUser::class
    ], version = 1,
    exportSchema = true
)
abstract class DatabaseGhostHome : RoomDatabase() {

    abstract fun ghostHomeUserDao(): GhostHomeUserDao

    companion object {
        @Volatile
        private var instance: DatabaseGhostHome? = null
        private const val DATABASE_NAME = "GhostHome.db"

        fun getInstance(context: Context): DatabaseGhostHome {
            if (instance == null) {
                synchronized(DatabaseGhostHome::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        DatabaseGhostHome::class.java,
                        DATABASE_NAME
                    )
                        .allowMainThreadQueries()
                        .addCallback(roomCallback)
                        .build()
                }
            }
            return instance!!
        }

        private val roomCallback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
            }
        }


    }
}