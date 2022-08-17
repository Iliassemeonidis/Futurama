package com.example.mytest.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mytest.databinding.ItemViewBinding
import com.example.mytest.model.data.FuturamaResultData

class MainAdapter(private val onClickListener: OnClickListener) :
    RecyclerView.Adapter<MainAdapter.FuturamaViewHolder>() {

    private var listOfFuturama: List<FuturamaResultData> = listOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setNewList(list: List<FuturamaResultData>) {
        listOfFuturama = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FuturamaViewHolder(
        ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: FuturamaViewHolder, position: Int) {
        holder.bind(listOfFuturama[position])
    }

    override fun getItemCount() = listOfFuturama.size

    inner class FuturamaViewHolder(private val vb: ItemViewBinding) :
        RecyclerView.ViewHolder(vb.root) {

        fun bind(futuramaResultData: FuturamaResultData) {
            vb.textViewName.text = futuramaResultData.character
            vb.textViewStatement.text = futuramaResultData.quote
            itemView.setOnClickListener {
                onClickListener.onClick(futuramaResultData)
            }
        }
    }

    interface OnClickListener {
        fun onClick(data: FuturamaResultData)
    }
}