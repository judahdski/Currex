package com.d3if0002.currex.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.Navigation
import com.d3if0002.currex.R
import com.d3if0002.currex.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), View.OnClickListener {

    private  var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }

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

        Log.d("Debugss", "onDestroy di HomeFragment dipanggil.")
        viewModel.schedulerUpdate(requireActivity().application)

        _binding = null
    }

    override fun onClick(p0: View?) {
        val buttonId: Int = when(p0?.id) {
            R.id.foreign_exchange_btn -> R.id.navigate_to_forex_fragment
            R.id.crypto_exchange_btn -> R.id.navigate_to_crypto_fragment
            R.id.konversi_mata_uang_btn -> R.id.navigate_to_convert_currency_fragment
            else -> 0
        }

        Navigation.findNavController(view!!).navigate(buttonId)
    }
}