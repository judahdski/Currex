package com.d3if0002.currex.ui.forex

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.d3if0002.currex.R
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
        with(holder) {
            with(binding) {
                symbolForex.text = dataTemp[position].toString()

                rateForex.text = "$${(dataTemp[position] * ((1..10).random()) * 64)}"

                Glide.with(itemView)
                    .load(R.drawable.eu)
                    .circleCrop()
                    .into(baseImgForex)

                Glide.with(itemView)
                    .load(R.drawable.btc)
                    .circleCrop()
                    .into(targetImgForex)
            }
        }
    }

    override fun getItemCount() = dataTemp.size
}