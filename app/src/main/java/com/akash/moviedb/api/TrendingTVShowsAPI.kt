package com.akash.moviedb.api

import com.akash.moviedb.model.TrendingTVShowsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TrendingTVShowsAPI {
    @GET("3/trending/tv/day?language=en-US&api_key={API_KEY}")
    fun getTVShows(
        @Path("API_KEY") API_KEY: String,
    ): Call<TrendingTVShowsResponse?>?
}