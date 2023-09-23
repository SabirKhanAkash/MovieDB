package com.akash.moviedb.api

import com.akash.moviedb.model.SingleTVShowDetailsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface SingleTVShowDetailsAPI {
    @GET("3/tv/{id}?api_key={API_KEY}")
    fun getSingleTVShow(
        @Path("id") id: Int,
        @Path("API_KEY") API_KEY: String,
    ): Call<SingleTVShowDetailsResponse?>?
}