package com.akash.moviedb.utils

import com.akash.moviedb.data.local.roomdb.entity.Show
import com.akash.moviedb.model.MovieDetails
import com.akash.moviedb.model.TVShowDetails

fun movieDetailsToShow(movieDetails: MovieDetails): Show {
    return Show(
        id = movieDetails.id,
        adult = movieDetails.adult,
        backdrop_path = movieDetails.backdrop_path,
        title = movieDetails.title,
        original_language = movieDetails.original_language,
        original_title = movieDetails.original_title,
        overview = movieDetails.overview,
        poster_path = movieDetails.poster_path,
        media_type = movieDetails.media_type,
        popularity = movieDetails.popularity,
        release_date = movieDetails.release_date,
        video = movieDetails.video,
        vote_average = movieDetails.vote_average,
        vote_count = movieDetails.vote_count,
        budget = movieDetails.budget,
        homepage = movieDetails.homepage,
        imdb_id = movieDetails.imdb_id,
        revenue = movieDetails.revenue,
        runtime = movieDetails.runtime,
        status = movieDetails.status,
        tagline = movieDetails.tagline,
        name = movieDetails.name,
        first_air_date = movieDetails.first_air_date,
        episode_run_time = movieDetails.episode_run_time,
        in_production = movieDetails.in_production,
        last_air_date = movieDetails.last_air_date,
        number_of_episodes = movieDetails.number_of_episodes,
        number_of_seasons = movieDetails.number_of_seasons,
        type = movieDetails.type,
        genres = movieDetails.genres
    )
}

fun tvDetailsToShow(tvShowDetails: TVShowDetails): Show {
    return Show(
        id = tvShowDetails.id,
        adult = tvShowDetails.adult,
        backdrop_path = tvShowDetails.backdrop_path,
        title = tvShowDetails.title,
        original_language = tvShowDetails.original_language,
        original_title = tvShowDetails.original_title,
        overview = tvShowDetails.overview,
        poster_path = tvShowDetails.poster_path,
        media_type = tvShowDetails.media_type,
        popularity = tvShowDetails.popularity,
        release_date = tvShowDetails.release_date,
        video = tvShowDetails.video,
        vote_average = tvShowDetails.vote_average,
        vote_count = tvShowDetails.vote_count,
        budget = tvShowDetails.budget,
        homepage = tvShowDetails.homepage,
        imdb_id = tvShowDetails.imdb_id,
        revenue = tvShowDetails.revenue,
        runtime = tvShowDetails.runtime,
        status = tvShowDetails.status,
        tagline = tvShowDetails.tagline,
        name = tvShowDetails.name,
        first_air_date = tvShowDetails.first_air_date,
        episode_run_time = tvShowDetails.episode_run_time,
        in_production = tvShowDetails.in_production,
        last_air_date = tvShowDetails.last_air_date,
        number_of_episodes = tvShowDetails.number_of_episodes,
        number_of_seasons = tvShowDetails.number_of_seasons,
        type = tvShowDetails.type,
        genres = tvShowDetails.genres
    )
}

