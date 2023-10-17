package com.akash.moviedb.data.local.roomdb.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.akash.moviedb.data.local.roomdb.Converters
import com.akash.moviedb.data.local.roomdb.dao.ShowDao
import com.akash.moviedb.data.local.roomdb.entity.Show

@Database(entities = [Show::class], version = 1)
@TypeConverters(Converters::class)
abstract class ShowDatabase : RoomDatabase() {

    abstract fun showDao(): ShowDao

    companion object {
        private var INSTANCE: ShowDatabase? = null

        fun getDatabase(context: Context): ShowDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    ShowDatabase::class.java,
                    "show_database"
                ).build()
            }
            return INSTANCE!!
        }
    }
}
