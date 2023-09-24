package com.akash.moviedb.repository

import com.akash.moviedb.BuildConfig
import com.akash.moviedb.api.SingleMovieDetailsAPI
import com.akash.moviedb.api.TrendingMoviesAPI
import com.akash.moviedb.model.SingleMovieDetailsResponse
import com.akash.moviedb.model.TrendingMoviesResponse
import retrofit2.Call

class SingleMovieRepository(private val service: SingleMovieDetailsAPI) {
    suspend fun getSingleMovie(selectedMovieId: Int): Call<SingleMovieDetailsResponse> {
        return service.getSingleMovie(selectedMovieId, BuildConfig.THE_MOVIE_DB_API_KEY)
    }
}

