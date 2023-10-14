package com.akash.moviedb.view.activity

import GenericApiResponse
import SingleTVShowViewModel
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.akash.moviedb.BuildConfig
import com.akash.moviedb.R
import com.akash.moviedb.adapter.GenreListAdapter
import com.akash.moviedb.databinding.ActivitySingleTvBinding
import com.akash.moviedb.utils.LoadingDialog
import com.akash.moviedb.utils.SharedPref
import com.akash.moviedb.viewmodel.viewmodelfactory.SingleTVShowViewModelFactory
import com.bumptech.glide.Glide
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import showTopToast

class SingleTVActivity : AppCompatActivity() {
    private var favChecked: Boolean = false
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

                    binding!!.favBtn.setOnClickListener {
                        if (favChecked) {
                            showTopToast(
                                applicationContext,
                                "Removed from favorites",
                                "short",
                                "negative"
                            )
                            binding!!.favBtn.setImageDrawable(
                                ContextCompat.getDrawable(
                                    applicationContext,
                                    R.drawable.fav_unchecked
                                )
                            )
                            favChecked = false
                        } else {
                            showTopToast(
                                applicationContext,
                                "Added to favorites",
                                "short",
                                "positive"
                            )
                            binding!!.favBtn.setImageDrawable(
                                ContextCompat.getDrawable(
                                    applicationContext,
                                    R.drawable.fav_checked
                                )
                            )
                            favChecked = true
                        }
                    }
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

        viewModel.singleTVVideoLiveData.observe(this@SingleTVActivity) { result ->
            when (result) {
                is GenericApiResponse.Success -> {
                    val resultData = result.data

                    var keyIndx = 0
                    var i = resultData.results.size - 1

                    while (i >= 0) {
                        if (resultData.results[i].name.contains(
                                "Official Trailer",
                                ignoreCase = true
                            ) || resultData.results[i].name.contains("Official", ignoreCase = true)
                        ) {
                            keyIndx = i
                            break
                        }
                        i--
                    }

                    binding!!.trailerView.addYouTubePlayerListener(object :
                        AbstractYouTubePlayerListener() {
                        override fun onReady(youTubePlayer: YouTubePlayer) {
                            try {
                                if (resultData.results.size > 0) {
                                    val videoId = resultData.results[keyIndx].key
                                    val site = resultData.results[keyIndx].site
                                    if (site == "YouTube") {
                                        youTubePlayer.loadVideo(videoId, 0f)
                                    } else {
                                        youTubePlayer.pause()
                                        binding!!.trailerView.visibility = View.GONE
                                    }
                                } else {
                                    youTubePlayer.pause()
                                    binding!!.trailerView.visibility = View.GONE
                                }

                            } catch (e: Exception) {
                                showTopToast(
                                    applicationContext,
                                    e.message.toString(),
                                    "short",
                                    "positive"
                                )
                            }
                        }
                    })

                    binding!!.favBtn.setOnClickListener {
                        if (favChecked) {
                            showTopToast(
                                applicationContext,
                                "Removed from favorites",
                                "short",
                                "negative"
                            )
                            binding!!.favBtn.setImageDrawable(
                                ContextCompat.getDrawable(
                                    applicationContext,
                                    R.drawable.fav_unchecked
                                )
                            )
                            favChecked = false
                        } else {
                            showTopToast(
                                applicationContext,
                                "Added to favorites",
                                "short",
                                "positive"
                            )
                            binding!!.favBtn.setImageDrawable(
                                ContextCompat.getDrawable(
                                    applicationContext,
                                    R.drawable.fav_checked
                                )
                            )
                            favChecked = true
                        }
                    }
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
        viewModel.fetchSingleTVTrailer(selectedMovieId)
        sharedPref.clearDataOnKey(applicationContext, "selectedTVId")
    }
}