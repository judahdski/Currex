package com.d3if0002.currex.ui.historyConversion

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.d3if0002.currex.databinding.FragmentHistoryConversionBinding
import com.d3if0002.currex.db.ExchangeDB
import com.d3if0002.currex.model.ProgressIndicator
import com.d3if0002.currex.repository.RepositoryDB

class HistoryConversionFragment : Fragment() {

    private val viewModel: HistoryConversionViewModel by lazy {
        val db = ExchangeDB.getInstance(requireContext())
        val repo = RepositoryDB(db.dao)
        val factory = HistoryConversionViewModelFactory(repo)
        ViewModelProvider(requireActivity(), factory)[HistoryConversionViewModel::class.java]
    }

    private lateinit var binding: FragmentHistoryConversionBinding
    private lateinit var myAdapter: HistoryConversionAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryConversionBinding.inflate(inflater)

        setAdapter()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.conversionList.observe(requireActivity()) {
            myAdapter.updateHistoryList(it)
        }
    }

    private fun setAdapter() {
        val rv = binding.historyConvertRv
        myAdapter = HistoryConversionAdapter()
        rv.adapter = myAdapter
        rv.layoutManager = LinearLayoutManager(requireContext())
        rv.setHasFixedSize(true)
    }
}
