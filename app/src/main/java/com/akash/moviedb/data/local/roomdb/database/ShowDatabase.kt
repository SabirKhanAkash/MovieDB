package com.akash.moviedb.data.local.roomdb.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.akash.moviedb.data.local.roomdb.dao.ShowDao
import com.akash.moviedb.data.local.roomdb.entity.Show

@Database(entities = [Show::class], version = 1, exportSchema = false)
abstract class ShowDatabase : RoomDatabase() {
    abstract fun showDao(): ShowDao

    companion object {
        @Volatile
        private var INSTANCE: ShowDatabase? = null

        fun getDatabase(ctx: Context): ShowDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    ctx.applicationContext,
                    ShowDatabase::class.java,
                    "show_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}