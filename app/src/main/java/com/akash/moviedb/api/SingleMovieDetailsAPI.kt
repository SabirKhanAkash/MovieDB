package com.akash.moviedb.api

import com.akash.moviedb.model.MovieDetails
import com.akash.moviedb.model.MovieVideoDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SingleMovieDetailsAPI {
    @GET("3/movie/{id}")
    fun getSingleMovie(
        @Path("id") id: Int,
        @Query("api_key") API_KEY: String,
    ): Call<MovieDetails>

    @GET("3/movie/{id}/videos")
    fun getSingleMovieVideo(
        @Path("id") id: Int,
        @Query("api_key") API_KEY: String,
    ): Call<MovieVideoDetails>
}