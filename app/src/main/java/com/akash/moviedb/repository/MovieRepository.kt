package com.akash.moviedb.repository

import com.akash.moviedb.BuildConfig
import com.akash.moviedb.api.TrendingMoviesAPI
import com.akash.moviedb.model.TrendingMoviesResponse
import retrofit2.Call

class MovieRepository(private val service: TrendingMoviesAPI) {
    suspend fun getTrendingMovies(): Call<TrendingMoviesResponse> {
        return service.getMovies(BuildConfig.THE_MOVIE_DB_API_KEY, 1)
    }
}

