package com.akash.moviedb.api

import com.akash.moviedb.model.TrendingMoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TrendingMoviesAPI {
    @GET("3/trending/movie/day")
    fun getMovies(
        @Query("api_key") API_KEY: String,
        @Query("page") page_no: Int
    ): Call<TrendingMoviesResponse>

    @GET("3/search/movie")
    fun getSearchedMovies(
        @Query("api_key") API_KEY: String,
        @Query("query") query: String,
        @Query("include_adult") include_adult: String,
        @Query("language") language: String,
        @Query("page") page_no: Int
    ): Call<TrendingMoviesResponse>
}