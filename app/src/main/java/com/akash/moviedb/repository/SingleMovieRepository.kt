package com.akash.moviedb.repository

import com.akash.moviedb.BuildConfig
import com.akash.moviedb.api.SingleMovieDetailsAPI
import com.akash.moviedb.model.MovieDetails
import com.akash.moviedb.model.MovieVideoDetails
import retrofit2.Call

class SingleMovieRepository(private val service: SingleMovieDetailsAPI) {
    fun getSingleMovie(selectedMovieId: Int): Call<MovieDetails> {
        return service.getSingleMovie(selectedMovieId, BuildConfig.THE_MOVIE_DB_API_KEY)
    }

    fun getSingleMovieTrailer(selectedMovieId: Int): Call<MovieVideoDetails> {
        return service.getSingleMovieVideo(selectedMovieId, BuildConfig.THE_MOVIE_DB_API_KEY)
    }
}

