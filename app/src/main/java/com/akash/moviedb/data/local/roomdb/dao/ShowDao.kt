package com.akash.moviedb.data.local.roomdb.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.akash.moviedb.data.local.roomdb.entity.Show

@Dao
interface ShowDao {
    @Insert
    fun insert(show: Show)

    @Delete
    fun delete(show: Show)

    @Query("SELECT * FROM favorite_shows")
    fun getFavShows(): List<Show>
}