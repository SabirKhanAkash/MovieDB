package com.akash.moviedb.utils

import android.content.Context
import com.akash.moviedb.data.local.roomdb.database.ShowDatabase
import com.akash.moviedb.model.MovieDetails
import com.akash.moviedb.model.TVShowDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

fun removeShowFromDatabase(context: Context, movieDetails: MovieDetails) {
    val show = movieDetailsToShow(movieDetails)
    val db = ShowDatabase.getDatabase(context)
    val favoriteShowDao = db.showDao()

    GlobalScope.launch(Dispatchers.IO) {
        favoriteShowDao.delete(show)
    }
}

fun removeShowFromDatabase(context: Context, tvShowDetails: TVShowDetails) {
    val show = tvDetailsToShow(tvShowDetails)
    val db = ShowDatabase.getDatabase(context)
    val favoriteShowDao = db.showDao()

    GlobalScope.launch(Dispatchers.IO) {
        favoriteShowDao.delete(show)
    }
}