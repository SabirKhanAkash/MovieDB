package com.akash.moviedb.view.fragment

import GenericApiResponse
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.akash.moviedb.adapter.TVAdapter
import com.akash.moviedb.databinding.FragmentTvShowBinding
import com.akash.moviedb.utils.LoadingDialog
import com.akash.moviedb.viewmodel.TVShowViewModel
import com.akash.moviedb.viewmodel.viewmodelfactory.TVShowViewModelFactory
import showTopToast

class TVShowFragment : Fragment() {

    companion object {
        fun newInstance() = TVShowFragment()
    }

    private var pageNo: Int = 1
    private val loadingDialog: LoadingDialog = LoadingDialog(this@TVShowFragment)
    private var binding: FragmentTvShowBinding? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var tvAdapter: TVAdapter
    private lateinit var viewModel: TVShowViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvShowBinding.inflate(layoutInflater)

        recyclerView = binding!!.tvRecyclerView
        viewModel = ViewModelProvider(this, TVShowViewModelFactory())[TVShowViewModel::class.java]
        tvAdapter = TVAdapter(requireContext(), emptyList())

        recyclerView.adapter = tvAdapter

        viewModel.tvLiveData.observe(viewLifecycleOwner) { result ->
            when (result) {
                is GenericApiResponse.Success -> {
                    val movies = result.data
                    if (movies.isNotEmpty()) {
                        binding!!.tvEmptiness.visibility = View.INVISIBLE
                    }
                    tvAdapter.updateData(movies)
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

        viewModel.fetchTrendingTVShows(pageNo)

        return binding!!.root
    }

    private fun updateUI() {
        if(pageNo == 1) {
            binding!!.prevBtn.isEnabled = false
        }
        if(pageNo == 500) {
            binding!!.nextBtn.isEnabled = false
        }
        else {
            binding!!.prevBtn.isEnabled = true
            binding!!.nextBtn.isEnabled = true
        }
    }

    private fun handleClickEvent() {
        binding!!.pageIndicator.setOnLongClickListener {
            pageNo = 1
            viewModel.fetchTrendingTVShows(pageNo)
            binding!!.pageIndicator.text = pageNo.toString()
            true
        }

        binding!!.prevBtn.setOnClickListener {
            if(pageNo > 1) {
                pageNo--
                viewModel.fetchTrendingTVShows(pageNo)
                binding!!.pageIndicator.text = pageNo.toString()
            }
        }

        binding!!.nextBtn.setOnClickListener {
            if(pageNo < 500) {
                pageNo++
                viewModel.fetchTrendingTVShows(pageNo)
                binding!!.pageIndicator.text = pageNo.toString()
            }
        }
    }
}