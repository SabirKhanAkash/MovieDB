package com.akash.moviedb.model

data class Episode(
    var id: Int,
    var vote_average: Double,
    var vote_count: Int,
    var name: String,
    var overview: String,
    var air_date: String,
    var episode_number: Int,
    var episode_type: String,
    var production_code: String,
    var runtime: Int?,
    var season_number: Int,
    var show_id: Int,
    var still_path: String
)
