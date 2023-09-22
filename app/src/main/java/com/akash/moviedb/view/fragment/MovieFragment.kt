package com.akash.moviedb.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.akash.moviedb.R
import com.akash.moviedb.databinding.FragmentMovieBinding
import com.akash.moviedb.utils.LoadingDialog
import com.akash.moviedb.viewmodel.MovieViewModel

class MovieFragment : Fragment() {

    companion object {
        fun newInstance() = MovieFragment()
    }
    val loadingDialog: LoadingDialog = LoadingDialog(this@MovieFragment)
    private var binding: FragmentMovieBinding? = null
    private lateinit var viewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        loadingDialog.startFragmentLoading()
        Log.i("TAG","HELLO MOVIE")
        loadingDialog.dismissLoading()
        binding = FragmentMovieBinding.inflate(layoutInflater)
        return binding!!.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]
        // TODO: Use the ViewModel
    }

}