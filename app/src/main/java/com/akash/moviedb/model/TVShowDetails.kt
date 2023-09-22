package com.akash.moviedb.model

import java.util.ArrayList

class TVShowDetails {
    var adult: Boolean = false
    lateinit var backdrop_path: String
    var id: Int = 0
    lateinit var name: String
    lateinit var original_language: String
    lateinit var original_name: String
    lateinit var overview: String
    lateinit var poster_path: String
    lateinit var media_type: String
    lateinit var genre_ids: ArrayList<Int>
    var popularity: Double = 0.0
    lateinit var first_air_date: String
    var vote_average: Double = 0.0
    var vote_count: Int = 0
    lateinit var origin_country: ArrayList<String>
}
