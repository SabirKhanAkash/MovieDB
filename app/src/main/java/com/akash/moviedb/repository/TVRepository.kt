package com.akash.moviedb.repository

import com.akash.moviedb.BuildConfig
import com.akash.moviedb.api.TrendingTVShowsAPI
import com.akash.moviedb.model.TrendingTVShowsResponse
import retrofit2.Call

class TVRepository(private val service: TrendingTVShowsAPI) {
    fun getTrendingTVShows(pageNo: Int): Call<TrendingTVShowsResponse> {
        return service.getTVShows(BuildConfig.THE_MOVIE_DB_API_KEY, pageNo)
    }

    fun getSearchedTVShows(query: String, pageNo: Int): Call<TrendingTVShowsResponse> {
        return service.getSearchedTVShows(
            BuildConfig.THE_MOVIE_DB_API_KEY,
            query,
            "false",
            "en-US",
            pageNo
        )
    }
}

