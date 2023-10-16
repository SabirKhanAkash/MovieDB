package com.akash.moviedb.data.local.roomdb.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.akash.moviedb.model.BelongsToCollection
import com.akash.moviedb.model.Episode
import com.akash.moviedb.model.Genre
import com.akash.moviedb.model.Language
import com.akash.moviedb.model.Network
import com.akash.moviedb.model.ProductionCompany
import com.akash.moviedb.model.ProductionCountry
import com.akash.moviedb.model.Season
import com.akash.moviedb.model.TVSeriesCreator

@Entity(tableName = "favorite_shows")
data class Show(
    @PrimaryKey val id: Int,
    var adult: Boolean,
    var backdrop_path: String,
    var title: String,
    var original_language: String,
    var original_title: String,
    var overview: String,
    var poster_path: String,
    var media_type: String,
    var genre_ids: ArrayList<Int>,
    var popularity: Double,
    var release_date: String,
    var video: Boolean,
    var vote_average: Double,
    var vote_count: Int,

    var belongs_to_collection: BelongsToCollection,
    var budget: Int,
    var genres: ArrayList<Genre>,
    var homepage: String,
    var imdb_id: String,
    var production_companies: ArrayList<ProductionCompany>,
    var production_countries: ArrayList<ProductionCountry>,
    var revenue: Int,
    var runtime: Int,
    var spoken_languages: ArrayList<Language>,
    var status: String,
    var tagline: String,

    var name: String,
    var original_name: String,
    var first_air_date: String,
    var origin_country: ArrayList<String>,

    var created_by: ArrayList<TVSeriesCreator>,
    var episode_run_time: ArrayList<Int>,
    var in_production: Boolean,
    var languages: ArrayList<String>,
    var last_air_date: String,
    var last_episode_to_air: Episode,
    var next_episode_to_air: Episode,
    var networks: ArrayList<Network>,
    var number_of_episodes: Int,
    var number_of_seasons: Int,
    var seasons: ArrayList<Season>,
    var type: String
)