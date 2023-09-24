package com.akash.moviedb.viewmodel.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.akash.moviedb.network.RetrofitClient
import com.akash.moviedb.repository.TVRepository
import com.akash.moviedb.viewmodel.TVShowViewModel

class TVShowViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TVShowViewModel::class.java)) {
            val repository = TVRepository(RetrofitClient.getTrendingTVShowsInterfaceService())
            return TVShowViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

