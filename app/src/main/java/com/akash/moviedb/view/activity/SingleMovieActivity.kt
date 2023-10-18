package com.akash.moviedb.view.activity

import GenericApiResponse
import SingleMovieViewModel
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.akash.moviedb.BuildConfig
import com.akash.moviedb.R
import com.akash.moviedb.adapter.GenreListAdapter
import com.akash.moviedb.data.local.roomdb.database.ShowDatabase
import com.akash.moviedb.data.local.roomdb.entity.Show
import com.akash.moviedb.databinding.ActivitySingleMovieBinding
import com.akash.moviedb.model.MovieDetails
import com.akash.moviedb.utils.LoadingDialog
import com.akash.moviedb.utils.SharedPref
import com.akash.moviedb.utils.addShowToDatabase
import com.akash.moviedb.utils.isFavorite
import com.akash.moviedb.utils.removeShowFromDatabase
import com.akash.moviedb.viewmodel.viewmodelfactory.SingleMovieViewModelFactory
import com.bumptech.glide.Glide
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import showTopToast


class SingleMovieActivity : AppCompatActivity() {
    private var favChecked: Boolean? = null
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


        lifecycle.addObserver(binding!!.trailerView)

        val selectedMovieId = sharedPref.getInt(applicationContext, "selectedMovieId")
        viewModel =
            ViewModelProvider(this, SingleMovieViewModelFactory())[SingleMovieViewModel::class.java]
        genreView = binding!!.genreView
        genreListAdapter = GenreListAdapter(applicationContext, emptyList())
        genreView.adapter = genreListAdapter

        viewModel.fetchSingleMovieDetails(selectedMovieId)
        viewModel.fetchSingleMovieTrailer(selectedMovieId)

        viewModel.singleMovieLiveData.observe(this@SingleMovieActivity) { result ->
            when (result) {
                is GenericApiResponse.Success -> {
                    val resultData = result.data
                    genreListAdapter.updateData(resultData.genres!!)
                    favChecked = isFavorite(applicationContext, resultData.id!!)

                    Glide.with(applicationContext)
                        .load(BuildConfig.POSTER_BASE_URL + resultData.backdrop_path).into(
                            binding!!.moviePoster
                        )
                    binding!!.trailerView.addYouTubePlayerListener(object :
                        AbstractYouTubePlayerListener() {
                        override fun onReady(youTubePlayer: YouTubePlayer) {
                            val videoId = "AjLKTz81bj8"
                            youTubePlayer.loadVideo(videoId, 0f)
                        }
                    })

                    binding!!.movieTitle.text = resultData.original_title
                    binding!!.tagline.text = resultData.tagline
                    binding!!.popularity.text = "Popularity Score: ${resultData.popularity}"
                    binding!!.runtime.text = resultData.runtime.toString() + " min"
                    binding!!.language.text = resultData.original_language
                    binding!!.releaseDate.text = resultData.release_date
                    binding!!.movieOverview.text = resultData.overview

                    binding!!.favBtn.setOnClickListener {
                        if (favChecked!!) {
                            GlobalScope.launch(Dispatchers.IO) {
                                removeShowFromDatabase(applicationContext, resultData)
                            }
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
                            GlobalScope.launch(Dispatchers.IO) {
                                addShowToDatabase(applicationContext, resultData)
                            }
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

        viewModel.singleMovieVideoLiveData.observe(this@SingleMovieActivity) { result ->
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
                        if (favChecked!!) {
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
        sharedPref.clearDataOnKey(applicationContext, "selectedMovieId")
    }
}