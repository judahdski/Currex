package com.d3if0002.currex.ui.historyConversion

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.d3if0002.currex.databinding.HistoryConversionLayoutItemBinding
import com.d3if0002.currex.db.ConversionEntity

class HistoryConversionAdapter
    : RecyclerView.Adapter<HistoryConversionAdapter.HistoryConversionViewHolder>() {
    private val historyList = mutableListOf<ConversionEntity>()

    fun updateHistoryList(newList: List<ConversionEntity>) {
        historyList.clear()
        historyList.addAll(newList)
        notifyDataSetChanged()
    }

    class HistoryConversionViewHolder(val binding: HistoryConversionLayoutItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryConversionViewHolder {
        val binding = HistoryConversionLayoutItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HistoryConversionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryConversionViewHolder, position: Int) {
        val item = historyList[position]

        with(holder) {
            with(binding) {
                baseValue.text = item.toString()
                toValue.text = item.toString()
                amountValue.text = item.toString()
                convertedValue.text = item.toString()
            }
        }
    }

    override fun getItemCount() = historyList.size

}