package com.akash.moviedb.view.fragment

import GenericApiResponse
import MovieViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.akash.moviedb.adapter.MovieAdapter
import com.akash.moviedb.databinding.FragmentMovieBinding
import com.akash.moviedb.utils.LoadingDialog
import com.akash.moviedb.viewmodel.viewmodelfactory.MovieViewModelFactory
import showTopToast

class MovieFragment : Fragment() {

    companion object {
        fun newInstance() = MovieFragment()
    }

    val loadingDialog: LoadingDialog = LoadingDialog(this@MovieFragment)
    private var binding: FragmentMovieBinding? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var viewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieBinding.inflate(layoutInflater)
        recyclerView = binding!!.recyclerView
        viewModel = ViewModelProvider(this, MovieViewModelFactory())[MovieViewModel::class.java]
        movieAdapter = MovieAdapter(emptyList())

        recyclerView.adapter = movieAdapter

        viewModel.moviesLiveData.observe(viewLifecycleOwner) { result ->
            when (result) {
                is GenericApiResponse.Success -> {
                    val movies = result.data
                    if (movies.isNotEmpty()) {
                        binding!!.movieEmptiness.visibility = View.INVISIBLE
                    }
                    movieAdapter.updateData(movies)
                }

                is GenericApiResponse.Error -> {
                    showTopToast(
                        requireContext(),
                        "Sorry! something went wrong :(",
                        "short",
                        "neutral"
                    )
                }

                else -> {
                    showTopToast(requireContext(), "Sorry! 404 not found :(", "short", "neutral")
                }
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                loadingDialog.startFragmentLoading()
            } else {
                loadingDialog.dismissLoading()
            }
        }

        viewModel.fetchTrendingMovies()
        return binding!!.root
    }

}