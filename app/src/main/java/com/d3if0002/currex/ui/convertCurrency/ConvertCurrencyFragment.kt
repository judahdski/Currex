package com.d3if0002.currex.ui.convertCurrency

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.d3if0002.currex.databinding.FragmentConvertCurrencyBinding

class ConvertCurrencyFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentConvertCurrencyBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConvertCurrencyBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUI()
    }

    private fun setUI() {
        val fromEditText = binding.fromCurrencyTf
        val toEditText = binding.toCurrencyTf
        val amountEditText = binding.amountCurrencyTf
        val convertBtn = binding.convertCurrencyBtn

        convertBtn.setOnClickListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClick(p0: View?) {
        TODO("call convert currency service function")
    }
}