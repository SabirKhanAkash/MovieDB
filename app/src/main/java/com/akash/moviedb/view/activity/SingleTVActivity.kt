package com.akash.moviedb.view.activity

import GenericApiResponse
import SingleTVShowViewModel
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.akash.moviedb.BuildConfig
import com.akash.moviedb.adapter.GenreListAdapter
import com.akash.moviedb.databinding.ActivitySingleTvBinding
import com.akash.moviedb.utils.LoadingDialog
import com.akash.moviedb.utils.SharedPref
import com.akash.moviedb.viewmodel.viewmodelfactory.SingleTVShowViewModelFactory
import com.bumptech.glide.Glide
import showTopToast

class SingleTVActivity : AppCompatActivity() {
    private val loadingDialog: LoadingDialog = LoadingDialog(this@SingleTVActivity)
    private val sharedPref: SharedPref = SharedPref()
    private var binding: ActivitySingleTvBinding? = null
    private lateinit var genreView: RecyclerView
    private lateinit var genreListAdapter: GenreListAdapter
    private lateinit var viewModel: SingleTVShowViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingleTvBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        val selectedMovieId = sharedPref.getInt(applicationContext, "selectedTVId")
        viewModel =
            ViewModelProvider(
                this,
                SingleTVShowViewModelFactory()
            )[SingleTVShowViewModel::class.java]
        genreView = binding!!.genreView
        genreListAdapter = GenreListAdapter(applicationContext, emptyList())
        genreView.adapter = genreListAdapter
        var x = 0

        viewModel.singleTVLiveData.observe(this@SingleTVActivity) { result ->
            when (result) {
                is GenericApiResponse.Success -> {
                    val resultData = result.data
                    genreListAdapter.updateData(resultData.genres)
                    Glide.with(applicationContext)
                        .load(BuildConfig.POSTER_BASE_URL + resultData.backdrop_path).into(
                            binding!!.moviePoster
                        )
                    binding!!.movieTitle.text = resultData.original_name
                    binding!!.type.text = resultData.type
                    binding!!.popularity.text = "Popularity Score: ${resultData.popularity}"
                    if (resultData.episode_run_time.toString() == "[]")
                        binding!!.runtime.text = "N/A"
                    else
                        binding!!.runtime.text =
                            (resultData.episode_run_time.toString()).replace("[", "")
                                .replace("]", "") + " min"
                    binding!!.language.text = resultData.original_language
                    binding!!.noOfSeason.text = resultData.number_of_seasons.toString()
                    binding!!.noOfEps.text = resultData.number_of_episodes.toString()
                    binding!!.firstAirDate.text = resultData.first_air_date
                    binding!!.lastAirDate.text = resultData.last_air_date
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

        viewModel.fetchSingleTVDetails(selectedMovieId)
        sharedPref.clearDataOnKey(applicationContext, "selectedTVId")
    }
}