package com.akash.moviedb.data.local.roomdb.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_shows")
data class Show(
    @PrimaryKey val id: Int,
    var adult: Boolean?,
    var backdrop_path: String?,
    var title: String?,
    var original_language: String?,
    var original_title: String?,
    var overview: String?,
    var poster_path: String?,
    var media_type: String?,
    var popularity: Double?,
    var release_date: String?,
    var video: Boolean?,
    var vote_average: Double?,
    var vote_count: Int?,

    var budget: Int?,
//    var genres: ArrayList<Genre>,
    var homepage: String?,
    var imdb_id: String?,
    var revenue: Int?,
    var runtime: Int?,
    var status: String?,
    var tagline: String?,

//    var name: String,
//    var first_air_date: String,
//
////    var episode_run_time: ArrayList<Int>,
//    var in_production: Boolean,
//    var last_air_date: String,
//    var number_of_episodes: Int,
//    var number_of_seasons: Int,
//    var type: String
)