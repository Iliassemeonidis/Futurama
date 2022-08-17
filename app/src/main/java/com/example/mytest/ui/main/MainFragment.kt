package com.example.mytest.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.mytest.R
import com.example.mytest.databinding.MainFragmentBinding
import com.example.mytest.model.AppState
import com.example.mytest.model.data.FuturamaResultData
import com.example.mytest.ui.details.DetailsFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModel()
    private var vb: MainFragmentBinding? = null
    private val binding get() = vb!!
    private lateinit var adapter: MainAdapter

    private val onClickListener: MainAdapter.OnClickListener =
        object : MainAdapter.OnClickListener {
            override fun onClick(data: FuturamaResultData) {
                Navigation.findNavController(requireView())
                    .navigate(R.id.goToDetailsFragment, DetailsFragment.newInstance(data).arguments)
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        vb = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()

        initViewModel()
    }

    private fun initAdapter() {
        adapter = MainAdapter(onClickListener)
        vb?.futuramaList?.adapter = adapter
    }

    private fun initViewModel() {
        viewModel.subscribe().observe(viewLifecycleOwner) { renderData(it) }
        viewModel.getData()
    }


    private fun renderData(state: AppState) {
        when (state) {
            is AppState.Success -> {
                showViewWorking()
                adapter.setNewList(state.data)
            }
            is AppState.Loading -> {
                showViewLoading()
                binding.progressBarHorizontal.visibility = View.VISIBLE
                binding.progressBarRound.visibility = View.GONE
                binding.progressBarHorizontal.progress = state.progress
            }
            is AppState.Error -> {
                Toast.makeText(requireContext(), state.error.message, Toast.LENGTH_SHORT).show()
            }
            is AppState.Information -> {
                Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showViewWorking() {
        vb?.loadingFrameLayout?.visibility = View.GONE
    }

    private fun showViewLoading() {
        vb?.loadingFrameLayout?.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        vb = null
    }
}