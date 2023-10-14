package com.akash.moviedb.repository

import com.akash.moviedb.BuildConfig
import com.akash.moviedb.api.SingleTVShowDetailsAPI
import com.akash.moviedb.model.MovieVideoDetails
import com.akash.moviedb.model.TVShowDetails
import retrofit2.Call

class SingleTVShowRepository(private val service: SingleTVShowDetailsAPI) {
    fun getSingleTV(selectedTVId: Int): Call<TVShowDetails> {
        return service.getSingleTVShow(selectedTVId, BuildConfig.THE_MOVIE_DB_API_KEY)
    }

    fun getSingleTVTrailer(selectedTVId: Int): Call<MovieVideoDetails> {
        return service.getSingleTVVideo(selectedTVId, BuildConfig.THE_MOVIE_DB_API_KEY)
    }
}

