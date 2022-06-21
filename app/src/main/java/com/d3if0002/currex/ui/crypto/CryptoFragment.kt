package com.d3if0002.currex.ui.crypto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.d3if0002.currex.databinding.FragmentCryptoBinding
import com.d3if0002.currex.repository.RepositoryAPI

class CryptoFragment : Fragment() {
    private val viewModel: CryptoViewModel by lazy {
        val repo = RepositoryAPI()
        val factory = CryptoViewModelFactory(repo)
        ViewModelProvider(requireActivity(), factory).get(CryptoViewModel::class.java)
    }

    private var _binding: FragmentCryptoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCryptoBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()
        viewModel.rates.observe(viewLifecycleOwner) {
            val adapter = CryptoAdapter()
            adapter.updateRateData(it)
        }
    }

    private fun setAdapter() {
        val cryptoRv = binding.cryptoRv
        cryptoRv.adapter = CryptoAdapter()
        cryptoRv.layoutManager = LinearLayoutManager(requireContext())
        cryptoRv.setHasFixedSize(true)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}