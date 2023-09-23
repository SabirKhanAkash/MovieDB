package com.akash.moviedb.viewmodel.viewmodelfactory

import MovieViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.akash.moviedb.network.RetrofitClient
import com.akash.moviedb.repository.MovieRepository

class MovieViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            val repository = MovieRepository(RetrofitClient.getTrendingMoviesInterfaceService())
            return MovieViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

