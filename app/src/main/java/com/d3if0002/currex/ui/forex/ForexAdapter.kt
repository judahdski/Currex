package com.d3if0002.currex.ui.forex

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.d3if0002.currex.databinding.ForexLayoutItemBinding

class ForexAdapter : RecyclerView.Adapter<ForexAdapter.ForexViewHolder>() {
    private val dataTemp = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    class ForexViewHolder(val binding: ForexLayoutItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForexViewHolder {
        val binding =
            ForexLayoutItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ForexViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ForexViewHolder, position: Int) {
        holder.binding.textView.text = dataTemp[position].toString()
    }

    override fun getItemCount() = dataTemp.size
}