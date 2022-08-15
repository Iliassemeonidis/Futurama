package com.example.mytest.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import coil.api.load
import com.example.mytest.databinding.FragmentDetailsBinding
import com.example.mytest.model.data.FuturamaResultData

class DetailsFragment : Fragment() {

    private var vb: FragmentDetailsBinding? = null
    private val binding get() = vb!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        vb = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = arguments?.getSerializable(ARG_FUTURAMA) as FuturamaResultData
        vb?.image?.load(data.image)
        vb?.statements?.text = data.quote


        // TODO делать проверку на офлайн и онлайн
//        if (онлайн) {
//            showViewWorking()
//            vb?.image?.load(data.image)
//            vb?.statements?.text = data.quote
//        } else {
//            showViewLoading()
//            binding.progressBarHorizontal.visibility = View.VISIBLE
//            binding.progressBarRound.visibility = View.GONE
//            binding.progressBarHorizontal.progress =5
//        }
    }

    // TODO вынести в утелиты
    private fun showViewWorking() {
        vb?.loadingFrameLayout?.visibility = View.GONE
    }

    private fun showViewLoading() {
        vb?.loadingFrameLayout?.visibility = View.VISIBLE
    }

    companion object {
        private const val ARG_FUTURAMA = "FUTURAMA"

        fun newInstance(futuramaResultData: FuturamaResultData) =
            DetailsFragment().apply {
                arguments = bundleOf(ARG_FUTURAMA to futuramaResultData)
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        vb = null
    }
}