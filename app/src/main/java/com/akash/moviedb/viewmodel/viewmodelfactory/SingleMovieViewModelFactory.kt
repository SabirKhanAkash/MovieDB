package com.akash.moviedb.viewmodel.viewmodelfactory

import MovieViewModel
import SingleMovieViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.akash.moviedb.network.RetrofitClient
import com.akash.moviedb.repository.MovieRepository
import com.akash.moviedb.repository.SingleMovieRepository

class SingleMovieViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SingleMovieViewModel::class.java)) {
            val repository = SingleMovieRepository(RetrofitClient.getSingleMovieInterfaceService())
            return SingleMovieViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

