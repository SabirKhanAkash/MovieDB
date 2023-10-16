package com.akash.moviedb.data.local.roomdb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.akash.moviedb.data.local.roomdb.entity.Show

@Dao
interface ShowDao {
    @Query("SELECT * FROM favorite_shows")
    fun getFavShows(): List<Show>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(show: Show)

    suspend fun delete(show: Show)
}