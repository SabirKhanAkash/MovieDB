package com.akash.moviedb.repository

import com.akash.moviedb.BuildConfig
import com.akash.moviedb.api.TrendingTVShowsAPI
import com.akash.moviedb.model.TrendingTVShowsResponse
import retrofit2.Call

class TVRepository(private val service: TrendingTVShowsAPI) {
    suspend fun getTrendingTVShows(): Call<TrendingTVShowsResponse> {
        return service.getTVShows(BuildConfig.THE_MOVIE_DB_API_KEY, 1)
    }
}

