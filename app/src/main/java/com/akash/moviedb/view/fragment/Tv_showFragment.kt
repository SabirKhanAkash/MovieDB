package com.akash.moviedb.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.akash.moviedb.databinding.FragmentTvShowBinding
import com.akash.moviedb.utils.LoadingDialog
import com.akash.moviedb.viewmodel.TVShowViewModel

class Tv_showFragment : Fragment() {

//    companion object {
//        fun newInstance() = TVShowFragment()
//    }
    val loadingDialog: LoadingDialog = LoadingDialog(this@Tv_showFragment)
    private var binding: FragmentTvShowBinding? = null
    private lateinit var viewModel: TVShowViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvShowBinding.inflate(layoutInflater)
        Log.i("TAG","HELLO TV SHOW")
        return binding!!.root
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this)[TVShowViewModel::class.java]
//        // TODO: Use the ViewModel
//    }

}