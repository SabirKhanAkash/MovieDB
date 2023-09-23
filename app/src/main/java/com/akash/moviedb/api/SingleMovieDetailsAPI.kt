package com.akash.moviedb.api

import com.akash.moviedb.model.SingleMovieDetailsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SingleMovieDetailsAPI {
    @GET("3/movie/{id}")
    fun getSingleMovie(
        @Path("id") id: Int,
        @Query("api_key") API_KEY: String,
    ): Call<SingleMovieDetailsResponse>
}