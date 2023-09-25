import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akash.moviedb.model.MovieDetails
import com.akash.moviedb.model.TVShowDetails
import com.akash.moviedb.repository.SingleMovieRepository
import com.akash.moviedb.repository.SingleTVShowRepository
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SingleTVShowViewModel(private val repository: SingleTVShowRepository) : ViewModel() {
    val singleTVLiveData: MutableLiveData<GenericApiResponse<TVShowDetails>> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    fun fetchSingleTVDetails(selectedTVId: Int) {
        isLoading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getSingleTV(selectedTVId).execute()
                val responseBodyString = response.body()?.toString()
                Log.d("Response", responseBodyString ?: "Response body is null")
                try {
                    val singleMovieDetails: TVShowDetails = Gson().fromJson(responseBodyString, TVShowDetails::class.java)
                    Log.d("Response", "Successfully deserialized: $singleMovieDetails")
                }
                catch (e: Exception) {
                    Log.e("Response", "Error during deserialization", e)
                }
                if (response.isSuccessful) {
                    val singleTVDetails: TVShowDetails? = response.body()
                    if (singleTVDetails != null) {
                        singleTVLiveData.postValue(GenericApiResponse.Success(singleTVDetails))
                    } else {
                        isLoading.postValue(false)
                        singleTVLiveData.postValue(GenericApiResponse.Error("Movie details are null"))
                    }
                    isLoading.postValue(false)
                } else {
                    isLoading.postValue(false)
                    singleTVLiveData.postValue(GenericApiResponse.Error("Oops! Something went wrong. :("))
                }
            } catch (e: Exception) {
                isLoading.postValue(false)
                singleTVLiveData.postValue(GenericApiResponse.Error(e.message))
            }
        }
    }
}
