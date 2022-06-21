package com.d3if0002.currex.ui.crypto

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.d3if0002.currex.R
import com.d3if0002.currex.databinding.CryptoLayoutItemBinding
import com.d3if0002.currex.db.RateEntity

class CryptoAdapter : RecyclerView.Adapter<CryptoAdapter.CryptoViewHolder>() {
    private val dataTemp = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    private val rates = mutableListOf<RateEntity>()

    class CryptoViewHolder(val binding: CryptoLayoutItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
        val binding =
            CryptoLayoutItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CryptoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        with(holder) {
            with(binding) {
                symbolCrypto.text = dataTemp[position].toString()

                priceCrypto.text = "$${(dataTemp[position] * ((1..10).random()) * 100)}"

                Glide.with(itemView)
                    .load(R.drawable.eu)
                    .circleCrop()
                    .into(baseImgCrypto)

                Glide.with(itemView)
                    .load(R.drawable.btc)
                    .circleCrop()
                    .into(targetImgCrypto)
            }
        }
    }

    fun updateRateData(data: List<RateEntity>) {
        rates.clear()
        rates.addAll(data)
        notifyDataSetChanged()
    }

    override fun getItemCount() = dataTemp.size
}