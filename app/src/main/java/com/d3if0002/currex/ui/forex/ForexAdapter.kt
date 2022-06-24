package com.d3if0002.currex.ui.forex

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.d3if0002.currex.R
import com.d3if0002.currex.databinding.ForexLayoutItemBinding
import com.d3if0002.currex.db.RateEntity

class ForexAdapter : RecyclerView.Adapter<ForexAdapter.ForexViewHolder>() {
    private val rates = mutableListOf<RateEntity>()

    class ForexViewHolder(val binding: ForexLayoutItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForexViewHolder {
        val binding =
            ForexLayoutItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ForexViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ForexViewHolder, position: Int) {
        val item = rates[position]

        with(holder) {
            with(binding) {
                symbolForex.text = item.symbol

                rateForex.text = item.rate.toString()

                Glide.with(itemView)
                    .load(item.baseImg)
                    .error(R.drawable.exchange)
                    .circleCrop()
                    .into(baseImgForex)

                Glide.with(itemView)
                    .load(item.targetImg)
                    .error(R.drawable.exchange)
                    .circleCrop()
                    .into(targetImgForex)
            }
        }
    }

    fun updateRateList(data: List<RateEntity>) {
        rates.clear()
        rates.addAll(data)
        notifyDataSetChanged()
    }

    override fun getItemCount() = rates.size
}