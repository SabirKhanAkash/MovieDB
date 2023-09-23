package com.akash.moviedb.model

data class TrendingMoviesResponse(
    var page: Int,
    var results: ArrayList<MovieDetails>,
    var total_pages: Int,
    var total_results: Int
)
