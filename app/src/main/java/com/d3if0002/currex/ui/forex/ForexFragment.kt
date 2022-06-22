package com.d3if0002.currex.ui.forex

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.d3if0002.currex.databinding.FragmentForexBinding
import com.d3if0002.currex.db.RateDB
import com.d3if0002.currex.model.ApiStatus
import com.d3if0002.currex.repository.RepositoryDB

class ForexFragment : Fragment() {

    private val viewModel: ForexViewModel by lazy {
        val db = RateDB.getInstance(requireContext())
        val repo = RepositoryDB(db.dao)
        val factory = ForexViewModelFactory(repo)
        ViewModelProvider(this, factory)[ForexViewModel::class.java]
    }

    private var _binding: FragmentForexBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForexBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()
        viewModel.rates.observe(viewLifecycleOwner) {
            val adapter = ForexAdapter()
            adapter.updateAdapterUI(it)
        }
    }

    private fun setAdapter() {
        val forexRv = binding.forexRv

        forexRv.adapter = ForexAdapter()
        forexRv.layoutManager = LinearLayoutManager(requireContext())
        forexRv.setHasFixedSize(true)
    }

    fun updateProgress(status: ApiStatus) {
        when (status) {
            ApiStatus.LOADING -> {}
            ApiStatus.SUCCESS -> {}
            ApiStatus.FAILED -> {}
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}