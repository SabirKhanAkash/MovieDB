//package com.akash.moviedb.utils
//
//import com.akash.moviedb.data.local.roomdb.entity.Show
//import com.akash.moviedb.model.MovieDetails
//
//class ObjectMapping {
//    fun movieDetailsToShow(movieDetails: MovieDetails) : Show {
//        return Show(
//            id = movieDetails.id,
//            adult = movieDetails.adult,
//            backdrop_path = movieDetails.backdrop_path,
//            title = movieDetails.title,
//            original_language = movieDetails.original_language,
//            original_title = movieDetails.original_title,
//            overview = movieDetails.overview,
//            poster_path = movieDetails.poster_path,
//            media_type = movieDetails.media_type,
//            popularity = movieDetails.popularity,
//            release_date = movieDetails.release_date,
//            video = movieDetails.video,
//            vote_average = movieDetails.vote_average,
//            vote_count = movieDetails.vote_count,
//            budget = movieDetails.budget,
//            homepage = movieDetails.homepage,
//            imdb_id = movieDetails.imdb_id,
//            revenue = movieDetails.revenue,
//            runtime = movieDetails.runtime,
//            status = movieDetails.status,
//            tagline = movieDetails.tagline
//        )
//    }
//}