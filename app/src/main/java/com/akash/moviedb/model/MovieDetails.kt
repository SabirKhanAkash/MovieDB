package com.akash.moviedb.model

import java.util.ArrayList

class MovieDetails {
    var adult: Boolean = false
    lateinit var backdrop_path: String
    var id: Int = 0
    lateinit var title: String
    lateinit var original_language: String
    lateinit var original_title: String
    lateinit var overview: String
    lateinit var poster_path: String
    lateinit var media_type: String
    lateinit var genre_ids: ArrayList<Int>
    var popularity: Double = 0.0
    lateinit var release_date: String
    var video: Boolean = false
    var vote_average: Double = 0.0
    var vote_count: Int = 0

    var belongs_to_collection: String? = null
    var budget: Int = 0
    lateinit var genres: ArrayList<Genre>
    var homepage: String? = null
    var imdb_id: String? = null
    lateinit var production_companies: ArrayList<ProductionCompany>
    lateinit var production_countries: ArrayList<ProductionCountry>
    var revenue: Int = 0
    var runtime: Int = 0
    lateinit var spoken_languages: ArrayList<Language>
    var status: String? = null
    var tagline: String? = null
}
