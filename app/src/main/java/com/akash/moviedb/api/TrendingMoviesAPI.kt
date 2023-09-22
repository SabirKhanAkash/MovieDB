package com.akash.moviedb.api

import com.akash.moviedb.model.TrendingMoviesResponse
import com.akash.moviedb.model.TrendingTVShowsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TrendingMoviesAPI {
    @GET("3/trending/movie/day?language=en-US&api_key={API_KEY}&page={page_no}")
    fun getTVShows(
        @Path("API_KEY") API_KEY: String,
        @Path("page_no") page_no: Int
    ): Call<TrendingMoviesResponse?>?
}