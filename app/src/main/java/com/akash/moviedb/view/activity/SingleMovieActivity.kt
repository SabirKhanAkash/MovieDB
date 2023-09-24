package com.akash.moviedb.view.activity

import SingleMovieViewModel
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.akash.moviedb.BuildConfig
import com.akash.moviedb.adapter.GenreListAdapter
import com.akash.moviedb.adapter.MovieAdapter
import com.akash.moviedb.databinding.ActivitySingleMovieBinding
import com.akash.moviedb.utils.LoadingDialog
import com.akash.moviedb.utils.SharedPref
import com.akash.moviedb.viewmodel.viewmodelfactory.MovieViewModelFactory
import com.akash.moviedb.viewmodel.viewmodelfactory.SingleMovieViewModelFactory
import com.bumptech.glide.Glide
import showTopToast

class SingleMovieActivity : AppCompatActivity() {
    private val loadingDialog: LoadingDialog = LoadingDialog(this@SingleMovieActivity)
    private val sharedPref: SharedPref = SharedPref()
    private var binding: ActivitySingleMovieBinding? = null
    private lateinit var genreView: RecyclerView
    private lateinit var genreListAdapter: GenreListAdapter
    private lateinit var viewModel: SingleMovieViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingleMovieBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        val selectedMovieId = sharedPref.getInt(applicationContext, "selectedMovieId")
        viewModel = ViewModelProvider(this, SingleMovieViewModelFactory())[SingleMovieViewModel::class.java]
        genreView = binding!!.genreView
        genreListAdapter = GenreListAdapter(applicationContext, emptyList())
        genreView.adapter = genreListAdapter
        viewModel.singleMovieLiveData.observe(this@SingleMovieActivity) { result ->
            when (result) {
                is GenericApiResponse.Success -> {
                    val resultData = result.data[0]
                    var xx = 0
                    Glide.with(applicationContext).load(BuildConfig.POSTER_BASE_URL + resultData.backdrop_path).into(
                        binding!!.moviePoster)
                    binding!!.movieTitle.text = resultData.original_title
                    binding!!.tagline.text = resultData.tagline
                    binding!!.popularity.text = "Popularity Score: ${resultData.popularity}"
                    binding!!.runtime.text = resultData.runtime.toString()
                    binding!!.language.text = resultData.original_language
                    binding!!.releaseDate.text = resultData.release_date
                    binding!!.movieOverview.text = resultData.overview
                }

                is GenericApiResponse.Error -> {
                    showTopToast(
                        applicationContext,
                        "Sorry! something went wrong :(",
                        "short",
                        "neutral"
                    )
                }

                else -> {
                    showTopToast(applicationContext, "Sorry! 404 not found :(", "short", "neutral")
                }
            }
        }

        viewModel.isLoading.observe(this) { isLoading ->
            if (isLoading) {
                loadingDialog.startLoading()
            } else {
                loadingDialog.dismissLoading()
            }
        }

        viewModel.fetchSingleMovieDetails(selectedMovieId)
        sharedPref.clearDataOnKey(applicationContext, "selectedMovieId")
    }
}