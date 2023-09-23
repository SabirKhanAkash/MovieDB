package com.akash.moviedb.model

import java.util.ArrayList

data class TVShowDetails (
    var adult: Boolean,
    var backdrop_path: String,
    var id: Int,
    var name: String,
    var original_language: String,
    var original_name: String,
    var overview: String,
    var poster_path: String,
    var media_type: String,
    var genre_ids: ArrayList<Int>,
    var popularity: Double,
    var first_air_date: String,
    var vote_average: Double,
    var vote_count: Int,
    var origin_country: ArrayList<String>,

    var created_by: ArrayList<TVSeriesCreator>,
    var genre: ArrayList<Genre>,
    var homepage: String,
    var in_production: Boolean,
    var languages: ArrayList<String>,
    var last_air_date: String,
    var last_episode_to_air: Episode,
    var next_episode_to_air: Episode,
    var networks: ArrayList<Network>,
    var number_of_episodes: Int,
    var number_of_seasons: Int,
    var production_companies: ArrayList<ProductionCompany>,
    var production_countries: ArrayList<ProductionCountry>,
    var seasons: ArrayList<Season>,
    var spoken_languages: ArrayList<SpokenLanguage>,
    var status: String,
    var tagline: String,
    var type: String
)
