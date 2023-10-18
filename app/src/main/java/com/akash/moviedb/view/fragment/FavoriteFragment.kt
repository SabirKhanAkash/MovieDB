package com.akash.moviedb.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.akash.moviedb.adapter.FavShowAdapter
import com.akash.moviedb.data.local.roomdb.entity.Show
import com.akash.moviedb.databinding.FragmentFavoriteBinding
import com.akash.moviedb.utils.LoadingDialog
import com.akash.moviedb.utils.getShowFromRoomDB
import com.akash.moviedb.viewmodel.TVShowViewModel
import kotlinx.coroutines.Dispatchers
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

        GlobalScope.launch(Dispatchers.IO) {
            favShowAdapter = FavShowAdapter(requireContext(), emptyList())
            favShows = getShowFromRoomDB(requireContext())
            if (favShows!!.isNotEmpty()) {
                binding!!.tvEmptiness.visibility = View.INVISIBLE
                binding!!.tvRecyclerView.visibility = View.VISIBLE
                recyclerView.adapter = favShowAdapter
                favShowAdapter.updateData(favShows!!)
            }

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

        return binding!!.root
    }
}