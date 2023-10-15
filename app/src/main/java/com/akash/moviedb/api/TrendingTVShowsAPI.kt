package com.akash.moviedb.api

import com.akash.moviedb.model.TrendingTVShowsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TrendingTVShowsAPI {
    @GET("3/trending/tv/day")
    fun getTVShows(
        @Query("api_key") API_KEY: String,
        @Query("page") page_no: Int
    ): Call<TrendingTVShowsResponse>

    @GET("3/search/tv")
    fun getSearchedTVShows(
        @Query("api_key") API_KEY: String,
        @Query("query") query: String,
        @Query("include_adult") include_adult: String,
        @Query("language") language: String,
        @Query("page") page_no: Int
    ): Call<TrendingTVShowsResponse>
}