package com.akash.moviedb.api

import com.akash.moviedb.model.SingleTVShowDetailsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SingleTVShowDetailsAPI {
    @GET("3/tv/{id}")
    fun getSingleTVShow(
        @Path("id") id: Int,
        @Query("api_key") API_KEY: String,
    ): Call<SingleTVShowDetailsResponse>
}