package com.d3if0002.currex.ui.forex

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.d3if0002.currex.databinding.FragmentForexBinding
import com.d3if0002.currex.db.ExchangeDB
import com.d3if0002.currex.model.ProgressIndicator
import com.d3if0002.currex.repository.RepositoryDB

class ForexFragment : Fragment() {

    private val viewModel: ForexViewModel by lazy {
        val db = ExchangeDB.getInstance(requireContext())
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
        // TODO: check status datanya, klo true tampilin data dri db, klo false tampilin error text
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

    fun updateProgress(status: ProgressIndicator) {
        when (status) {
            ProgressIndicator.LOADING -> {}
            ProgressIndicator.SUCCESS -> {}
            ProgressIndicator.FAILED -> {}
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}