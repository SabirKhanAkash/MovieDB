package com.akash.moviedb.model

data class Season(
    var air_date: String,
    var episode_count: Int,
    var id: Int,
    var season_number: Int,
    var vote_average: Int,
    var name: String,
    var overview: String,
    var poster_path: String
)
