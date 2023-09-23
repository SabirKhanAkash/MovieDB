package com.akash.moviedb.api

import com.akash.moviedb.model.SingleMovieDetailsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface SingleMovieDetailsAPI {
    @GET("3/movie/{id}?api_key={API_KEY}")
    fun getSingleMovie(
        @Path("id") id: Int,
        @Path("API_KEY") API_KEY: String,
    ): Call<SingleMovieDetailsResponse?>?
}