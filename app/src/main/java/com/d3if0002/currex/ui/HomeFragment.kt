package com.d3if0002.currex.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.d3if0002.currex.R
import com.d3if0002.currex.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), View.OnClickListener {

    private  var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUI()
    }

    private fun setUI() {
        val forexBtn = binding.foreignExchangeBtn
        val cryptoExBtn = binding.cryptoExchangeBtn
        val convertCurrBtn = binding.konversiMataUangBtn

        forexBtn.setOnClickListener(this)
        cryptoExBtn.setOnClickListener(this)
        convertCurrBtn.setOnClickListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClick(p0: View?) {

    }
}