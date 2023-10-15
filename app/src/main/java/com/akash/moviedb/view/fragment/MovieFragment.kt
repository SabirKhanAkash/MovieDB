package com.akash.moviedb.view.fragment

import GenericApiResponse
import MovieViewModel
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.akash.moviedb.adapter.MovieAdapter
import com.akash.moviedb.databinding.FragmentMovieBinding
import com.akash.moviedb.utils.LoadingDialog
import com.akash.moviedb.utils.saveSearchSuggestionToDisk
import com.akash.moviedb.viewmodel.viewmodelfactory.MovieViewModelFactory
import showTopToast


class MovieFragment : Fragment() {

    companion object {
        fun newInstance() = MovieFragment()
    }

    private var query: String? = ""
    private var pageNo: Int = 1
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
        recyclerView = binding!!.movieRecyclerView
        viewModel = ViewModelProvider(this, MovieViewModelFactory())[MovieViewModel::class.java]
        movieAdapter = MovieAdapter(requireContext(), emptyList())

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

        updateUI()

        handleClickEvent()

        binding!!.pageIndicator.text = pageNo.toString()
        viewModel.fetchTrendingMovies(pageNo)
        return binding!!.root
    }

    private fun updateUI() {
        if (pageNo == 1) {
            binding!!.prevBtn.isEnabled = false
        }
        if (pageNo == 500) {
            binding!!.nextBtn.isEnabled = false
        } else {
            binding!!.prevBtn.isEnabled = true
            binding!!.nextBtn.isEnabled = true
        }
    }

    private fun handleClickEvent() {
        binding!!.pageIndicator.setOnLongClickListener {
            pageNo = 1
            viewModel.fetchTrendingMovies(pageNo)
            binding!!.pageIndicator.text = pageNo.toString()
            binding!!.movieSearchBar.closeSearch()
            true
        }

        binding!!.prevBtn.setOnClickListener {
            if (pageNo > 1) {
                pageNo--
                if (query.toString().length > 0)
                    viewModel.fetchSearchedMovies(query.toString(), pageNo)
                else
                    viewModel.fetchTrendingMovies(pageNo)
                binding!!.pageIndicator.text = pageNo.toString()
            }
        }

        binding!!.nextBtn.setOnClickListener {
            if (pageNo < 500) {
                pageNo++
                if (query.toString().length > 0)
                    viewModel.fetchSearchedMovies(query.toString(), pageNo)
                else
                    viewModel.fetchTrendingMovies(pageNo)
                binding!!.pageIndicator.text = pageNo.toString()
            }
        }

        binding!!.movieSearchBar.addTextChangeListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun afterTextChanged(editable: Editable) {
                if (editable.isNotEmpty()) {
                    query = binding!!.movieSearchBar.text
                    viewModel.fetchSearchedMovies(query.toString(), pageNo)
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        saveSearchSuggestionToDisk(
            requireContext(),
            "movie",
            binding!!.movieSearchBar.lastSuggestions
        )
    }
}