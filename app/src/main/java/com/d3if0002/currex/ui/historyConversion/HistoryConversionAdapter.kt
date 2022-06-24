package com.d3if0002.currex.ui.historyConversion

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.d3if0002.currex.databinding.HistoryConversionLayoutItemBinding
import com.d3if0002.currex.db.ConversionEntity

class HistoryConversionAdapter
    : RecyclerView.Adapter<HistoryConversionAdapter.HistoryConversionViewHolder>() {

    private val historyList = mutableListOf<ConversionEntity>()

    fun updateHistoryList(newData: List<ConversionEntity>) {
        historyList.clear()
        historyList.addAll(newData)
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

        Log.d("DEBUGZZ", "$item")

        with(holder) {
            with(binding) {
                baseValue.text = item.baseCurr
                toValue.text = item.targetCurr
                amountValue.text = item.amount
                convertedValue.text = item.result
            }
        }
    }

    override fun getItemCount() = historyList.size
}