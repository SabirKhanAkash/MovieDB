// MovieViewModel.kt
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akash.moviedb.model.MovieDetails
import com.akash.moviedb.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel(private val repository: MovieRepository) : ViewModel() {

    val moviesLiveData: MutableLiveData<GenericApiResponse<List<MovieDetails>>> = MutableLiveData()

    fun fetchTrendingMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getTrendingMovies().execute()
                if (response.isSuccessful) {
                    val trendingMovies: List<MovieDetails> = response.body()!!.results
                    moviesLiveData.postValue(GenericApiResponse.Success(trendingMovies))
                } else {
                    moviesLiveData.postValue(GenericApiResponse.Error("Oops! Something went wrong. :("))
                }
            } catch (e: Exception) {
                moviesLiveData.postValue(GenericApiResponse.Error(e.message))
            }
        }
    }
}
