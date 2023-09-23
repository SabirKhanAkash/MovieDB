package com.akash.moviedb.model

data class TrendingTVShowsResponse(
    var page: Int,
    var results: ArrayList<TVShowDetails>,
    var total_pages: Int,
    var total_results: Int
)