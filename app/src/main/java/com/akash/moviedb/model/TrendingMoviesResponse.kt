package com.akash.moviedb.model

import java.util.ArrayList

data class TrendingMoviesResponse (
    var page: Int,
    var results: ArrayList<MovieDetails>,
    var total_pages: Int,
    var total_results: Int
)
