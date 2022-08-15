package com.example.mytest.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
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
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.main_container, DetailsFragment.newInstance(data))
                    .addToBackStack(null)
                    .commitAllowingStateLoss()
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
        adapter = MainAdapter(onClickListener)
        vb?.futuramaList?.adapter = adapter

//        TODO("сделать инициализацию через коин и все лишнее вынести в отдельный метод")
        viewModel.subscribe().observe(viewLifecycleOwner) { rendetData(it) }
        viewModel.getData()
    }


    private fun rendetData(state: AppState) {
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