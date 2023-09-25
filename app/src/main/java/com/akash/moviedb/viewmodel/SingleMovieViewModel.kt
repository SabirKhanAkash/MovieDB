import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akash.moviedb.model.MovieDetails
import com.akash.moviedb.repository.SingleMovieRepository
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SingleMovieViewModel(private val repository: SingleMovieRepository) : ViewModel() {
    val singleMovieLiveData: MutableLiveData<GenericApiResponse<MovieDetails>> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    fun fetchSingleMovieDetails(selectedMovieId: Int) {
        isLoading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getSingleMovie(selectedMovieId).execute()
                val responseBodyString = response.body()?.toString()
                Log.d("Response", responseBodyString ?: "Response body is null")
                try {
                    val singleMovieDetails: MovieDetails = Gson().fromJson(responseBodyString, MovieDetails::class.java)
                    Log.d("Response", "Successfully deserialized: $singleMovieDetails")
                }
                catch (e: Exception) {
                    Log.e("Response", "Error during deserialization", e)
                }
                if (response.isSuccessful) {
                    val singleMovieDetails: MovieDetails? = response.body()
                    if (singleMovieDetails != null) {
                        singleMovieLiveData.postValue(GenericApiResponse.Success(singleMovieDetails))
                    } else {
                        isLoading.postValue(false)
                        singleMovieLiveData.postValue(GenericApiResponse.Error("Movie details are null"))
                    }
                    isLoading.postValue(false)
                } else {
                    isLoading.postValue(false)
                    singleMovieLiveData.postValue(GenericApiResponse.Error("Oops! Something went wrong. :("))
                }
            } catch (e: Exception) {
                isLoading.postValue(false)
                singleMovieLiveData.postValue(GenericApiResponse.Error(e.message))
            }
        }
    }

}
