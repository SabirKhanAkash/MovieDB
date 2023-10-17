package com.akash.moviedb.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.akash.moviedb.adapter.FavShowAdapter
import com.akash.moviedb.data.local.roomdb.database.ShowDatabase
import com.akash.moviedb.data.local.roomdb.entity.Show
import com.akash.moviedb.databinding.FragmentFavoriteBinding
import com.akash.moviedb.utils.LoadingDialog
import com.akash.moviedb.viewmodel.TVShowViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FavoriteFragment : Fragment() {

    companion object {
        fun newInstance() = FavoriteFragment()
    }

    private var pageNo: Int = 1
    private val loadingDialog: LoadingDialog = LoadingDialog(this@FavoriteFragment)
    private var binding: FragmentFavoriteBinding? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var favShowAdapter: FavShowAdapter
    private lateinit var viewModel: TVShowViewModel
    private var favShows: List<Show>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(layoutInflater)

        recyclerView = binding!!.tvRecyclerView
//        viewModel = ViewModelProvider(this, TVShowViewModelFactory())[TVShowViewModel::class.java]


        GlobalScope.launch {
            favShowAdapter = FavShowAdapter(requireContext(), emptyList())
            favShows = getShowFromDatabase(requireContext())
            if (favShows!!.isNotEmpty()) {
                binding!!.tvEmptiness.visibility = View.INVISIBLE
            }
            binding!!.tvRecyclerView.visibility = View.VISIBLE
            favShowAdapter.updateData(favShows!!)
            recyclerView.adapter = favShowAdapter
        }


        /*        viewModel.tvLiveData.observe(viewLifecycleOwner) { result ->
                    when (result) {
                        is GenericApiResponse.Success -> {
                            val movies = result.data
                            // Gotta fix it to isNotEmpty()
                            if (movies.isEmpty()) {
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

                handleClickEvent()*/

//        binding!!.pageIndicator.text = pageNo.toString()
//        viewModel.fetchTrendingTVShows(pageNo)

        return binding!!.root
    }

    private fun getShowFromDatabase(context: Context): List<Show> {
        val db = ShowDatabase.getDatabase(context)
        val favoriteShowDao = db.showDao()

        return favoriteShowDao.getFavShows()
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
            viewModel.fetchTrendingTVShows(pageNo)
            binding!!.pageIndicator.text = pageNo.toString()
            true
        }

        binding!!.prevBtn.setOnClickListener {
            if (pageNo > 1) {
                pageNo--
                viewModel.fetchTrendingTVShows(pageNo)
                binding!!.pageIndicator.text = pageNo.toString()
            }
        }

        binding!!.nextBtn.setOnClickListener {
            if (pageNo < 500) {
                pageNo++
                viewModel.fetchTrendingTVShows(pageNo)
                binding!!.pageIndicator.text = pageNo.toString()
            }
        }
    }
}