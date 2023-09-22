package com.akash.moviedb.model

import java.util.ArrayList

class TrendingMoviesResponse {
    var page: Int = 0
    lateinit var results: ArrayList<MovieDetails>
    var total_pages: Int = 0
    var total_results: Int = 0
}
