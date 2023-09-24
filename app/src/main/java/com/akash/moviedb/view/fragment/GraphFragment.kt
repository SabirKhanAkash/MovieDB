package com.akash.moviedb.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.akash.moviedb.databinding.FragmentGraphBinding
import com.akash.moviedb.utils.LoadingDialog
import com.akash.moviedb.utils.readAndParseCSVData
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
        binding = FragmentGraphBinding.inflate(layoutInflater)

        val data = readAndParseCSVData(requireContext())
        val dataForSpikeGraph = data.mapIndexed { index, csvData ->
            Pair(index.toFloat(), csvData.close)
        }

        binding!!.customGraphView.setDataPoints(dataForSpikeGraph)
        return binding!!.root
    }
}