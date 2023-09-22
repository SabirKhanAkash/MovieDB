package com.akash.moviedb.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.akash.moviedb.R
import com.akash.moviedb.databinding.FragmentGraphBinding
import com.akash.moviedb.databinding.FragmentMovieBinding
import com.akash.moviedb.utils.LoadingDialog
import com.akash.moviedb.viewmodel.GraphViewModel

class GraphFragment : Fragment() {

    companion object {
        fun newInstance() = GraphFragment()
    }

    private var binding: FragmentGraphBinding? = null
    private lateinit var viewModel: GraphViewModel
    val loadingDialog: LoadingDialog = LoadingDialog(this@GraphFragment)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        loadingDialog.startFragmentLoading()
        Log.i("TAG","HELLO GRAPH")
        loadingDialog.dismissLoading()
        binding = FragmentGraphBinding.inflate(layoutInflater)
        return binding!!.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[GraphViewModel::class.java]
        // TODO: Use the ViewModel
    }

}