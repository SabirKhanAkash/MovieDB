import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akash.moviedb.model.MovieDetails
import com.akash.moviedb.repository.MovieRepository
import com.akash.moviedb.repository.SingleMovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SingleMovieViewModel(private val repository: SingleMovieRepository) : ViewModel() {
    val singleMovieLiveData: MutableLiveData<GenericApiResponse<List<MovieDetails>>>  = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    fun fetchSingleMovieDetails(selectedMovieId: Int) {
        isLoading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getSingleMovie(selectedMovieId).execute()
                Log.d("meo", response.toString())
                if (response.isSuccessful) {
                    val singleMovieDetails: List<MovieDetails> = response.body()!!.movieDetails
                    singleMovieLiveData.postValue(GenericApiResponse.Success(singleMovieDetails))
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
