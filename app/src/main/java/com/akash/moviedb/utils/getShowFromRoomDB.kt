package com.akash.moviedb.utils

import android.content.Context
import com.akash.moviedb.data.local.roomdb.database.ShowDatabase
import com.akash.moviedb.data.local.roomdb.entity.Show

fun getShowFromRoomDB(context: Context): List<Show> {
    val db = ShowDatabase.getDatabase(context)
    val favoriteShowDao = db.showDao()

    return favoriteShowDao.getFavShows()
}