package com.akash.moviedb.api

import com.akash.moviedb.model.SingleMovieDetailsResponse
import com.akash.moviedb.model.TrendingMoviesResponse
import com.akash.moviedb.model.TrendingTVShowsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface SingleMovieDetailsAPI {
    @GET("3/movie/{id}?api_key={API_KEY}")
    fun getTVShows(
        @Path("id") id: Int,
        @Path("API_KEY") API_KEY: String,
    ): Call<SingleMovieDetailsResponse?>?
}