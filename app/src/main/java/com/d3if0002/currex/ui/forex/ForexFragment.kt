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
import com.d3if0002.currex.repository.RepositoryDB
import com.d3if0002.currex.repository.RepositoryDataStore

class ForexFragment : Fragment() {

    private val viewModel: ForexViewModel by lazy {
        val db = ExchangeDB.getInstance(requireContext())
        val repoDB = RepositoryDB(db.dao)
        val repoDS = RepositoryDataStore(requireContext())
        val factory = ForexViewModelFactory(repoDB, repoDS)
        ViewModelProvider(this, factory)[ForexViewModel::class.java]
    }

    private var _binding: FragmentForexBinding? = null
    private val binding get() = _binding!!

    private lateinit var myAdapter: ForexAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForexBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myAdapter = ForexAdapter()
        setAdapter()

        var isEmptyData = false
        viewModel.apiDataStatus.observe(requireActivity()) {
            isEmptyData = it
        }

        viewModel.getRateList().observe(viewLifecycleOwner) {
            if (isEmptyData) {
                myAdapter.updateRateList(it)
            } else {
                binding.errorText.visibility = View.VISIBLE
            }
        }
    }

    private fun setAdapter() {
        val forexRv = binding.forexRv

        forexRv.adapter = myAdapter
        forexRv.layoutManager = LinearLayoutManager(requireContext())
        forexRv.setHasFixedSize(true)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}