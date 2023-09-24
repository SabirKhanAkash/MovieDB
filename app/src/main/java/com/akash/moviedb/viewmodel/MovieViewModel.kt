import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akash.moviedb.model.MovieDetails
import com.akash.moviedb.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel(private val repository: MovieRepository) : ViewModel() {

    val moviesLiveData: MutableLiveData<GenericApiResponse<List<MovieDetails>>> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    fun fetchTrendingMovies() {
        isLoading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getTrendingMovies().execute()
                if (response.isSuccessful) {
                    val trendingMovies: List<MovieDetails> = response.body()!!.results
                    if (trendingMovies.isNotEmpty()) {
                        isLoading.postValue(false)
                    }
                    moviesLiveData.postValue(GenericApiResponse.Success(trendingMovies))
                } else {
                    isLoading.postValue(false)
                    moviesLiveData.postValue(GenericApiResponse.Error("Oops! Something went wrong. :("))
                }
            } catch (e: Exception) {
                isLoading.postValue(false)
                moviesLiveData.postValue(GenericApiResponse.Error(e.message))
            }
        }
    }
}
