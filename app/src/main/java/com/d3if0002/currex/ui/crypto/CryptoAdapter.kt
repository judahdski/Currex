package com.d3if0002.currex.ui.crypto

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.d3if0002.currex.databinding.CryptoLayoutItemBinding

class CryptoAdapter : RecyclerView.Adapter<CryptoAdapter.CryptoViewHolder>() {
    private val dataTemp = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

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
            }
        }
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}