package com.d3if0002.currex.ui.historyConversion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.d3if0002.currex.databinding.FragmentHistoryConversionBinding
import com.d3if0002.currex.db.ExchangeDB
import com.d3if0002.currex.repository.RepositoryDB

class HistoryConversionFragment : Fragment() {

    private val viewModel: HistoryConversionViewModel by lazy {
        val db = ExchangeDB.getInstance(requireContext())
        val repo = RepositoryDB(db.dao)
        val factory = HistoryConversionViewModelFactory(repo)
        ViewModelProvider(requireActivity(), factory).get(HistoryConversionViewModel::class.java)
    }

    private lateinit var binding: FragmentHistoryConversionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryConversionBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRvAdapter()

        viewModel.conversionList.observe(requireActivity()) {
            HistoryConversionAdapter().updateHistoryList(it)
        }
    }

    private fun setRvAdapter() {
        val rv = binding.historyConvertRv
        rv.adapter = HistoryConversionAdapter()
        rv.setHasFixedSize(true)
    }
}
