package com.akash.moviedb.viewmodel

import GenericApiResponse
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akash.moviedb.model.MovieDetails
import com.akash.moviedb.model.TVShowDetails
import com.akash.moviedb.repository.MovieRepository
import com.akash.moviedb.repository.TVRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TVShowViewModel(private val repository: TVRepository) : ViewModel() {
    val tvLiveData: MutableLiveData<GenericApiResponse<List<TVShowDetails>>> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    fun fetchTrendingTVShows() {
        isLoading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getTrendingTVShows().execute()
                if (response.isSuccessful) {
                    val trendingTVShows: List<TVShowDetails> = response.body()!!.results
                    if (trendingTVShows.isNotEmpty()) {
                        isLoading.postValue(false)
                    }
                    tvLiveData.postValue(GenericApiResponse.Success(trendingTVShows))
                } else {
                    tvLiveData.postValue(GenericApiResponse.Error("Oops! Something went wrong. :("))
                }
            } catch (e: Exception) {
                tvLiveData.postValue(GenericApiResponse.Error(e.message))
            }
        }
    }
}