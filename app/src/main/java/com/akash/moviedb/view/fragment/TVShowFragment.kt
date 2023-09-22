package com.akash.moviedb.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.akash.moviedb.R
import com.akash.moviedb.viewmodel.TVShowViewModel

class TVShowFragment : Fragment() {

    companion object {
        fun newInstance() = TVShowFragment()
    }

    private lateinit var viewModel: TVShowViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[TVShowViewModel::class.java]
        // TODO: Use the ViewModel
    }

}