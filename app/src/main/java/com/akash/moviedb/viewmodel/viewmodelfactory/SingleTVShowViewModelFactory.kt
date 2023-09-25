package com.akash.moviedb.viewmodel.viewmodelfactory

import SingleMovieViewModel
import SingleTVShowViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.akash.moviedb.network.RetrofitClient
import com.akash.moviedb.repository.SingleMovieRepository
import com.akash.moviedb.repository.SingleTVShowRepository

class SingleTVShowViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SingleTVShowViewModel::class.java)) {
            val repository = SingleTVShowRepository(RetrofitClient.getSingleTVShowInterfaceService())
            return SingleTVShowViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

