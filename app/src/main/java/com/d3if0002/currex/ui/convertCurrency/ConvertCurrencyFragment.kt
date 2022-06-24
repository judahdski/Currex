package com.d3if0002.currex.ui.convertCurrency

import android.os.Bundle
import android.view.*
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.d3if0002.currex.R
import com.d3if0002.currex.databinding.FragmentConvertCurrencyBinding
import com.d3if0002.currex.db.ExchangeDB
import com.d3if0002.currex.model.ProgressIndicator
import com.d3if0002.currex.repository.RepositoryAPI
import com.d3if0002.currex.repository.RepositoryDB
import com.google.android.material.textfield.TextInputLayout

class ConvertCurrencyFragment : Fragment(), View.OnClickListener {

    private val viewModel: ConvertCurrencyViewModel by lazy {
        val db = ExchangeDB.getInstance(requireContext())
        val factory = ConvertCurrencyViewModelFactory(RepositoryAPI(), RepositoryDB(db.dao))
        ViewModelProvider(requireActivity(), factory)[ConvertCurrencyViewModel::class.java]
    }

    private var _binding: FragmentConvertCurrencyBinding? = null
    private val binding get() = _binding!!

    private lateinit var fromEditText: TextInputLayout
    private lateinit var toEditText: TextInputLayout
    private lateinit var amountEditText: TextInputLayout

    private lateinit var resultText: TextView
    private lateinit var progressBar: ProgressBar

    private var convertedResult: Double = 0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConvertCurrencyBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setUI()
        viewModel.result.observe(viewLifecycleOwner) {
            setResultText(it)
        }
        viewModel.status.observe(viewLifecycleOwner) {
            updateProgressBar(it)
        }
    }

    private fun setResultText(convertedResult: Any) {
        if (convertedResult is Double) {
            val target = toEditText.editText?.text.toString().uppercase()

            resultText.text = getString(R.string.result_convert, target, convertedResult)
            this.convertedResult = convertedResult
        } else {
            resultText.text = convertedResult.toString()
        }
    }

    private fun updateProgressBar(status: ProgressIndicator) {
        when (status) {
            ProgressIndicator.LOADING -> {
                progressBar.visibility = View.VISIBLE
            }
            ProgressIndicator.SUCCESS -> {
                progressBar.visibility = View.GONE
            }
            ProgressIndicator.FAILED -> {
                progressBar.visibility = View.GONE
            }
        }
    }

    private fun setUI() {
        fromEditText = binding.fromCurrencyTf
        toEditText = binding.toCurrencyTf
        amountEditText = binding.amountCurrencyTf
        resultText = binding.resultText
        progressBar = binding.progressBar2
        val convertBtn = binding.convertCurrencyBtn

        convertBtn.setOnClickListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClick(p0: View?) {
        if (p0?.id == R.id.convert_currency_btn) {
            viewModel.convertCurrency(
                base = fromEditText.editText?.text.toString().uppercase(),
                target = toEditText.editText?.text.toString().uppercase(),
                amount = amountEditText.editText?.text.toString().toInt()
            )

            viewModel.insertConversionViewModel(
                base = fromEditText.editText?.text.toString().uppercase(),
                target = toEditText.editText?.text.toString().uppercase(),
                amount = amountEditText.editText?.text.toString().toInt(),
                result = convertedResult
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.convert_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.history_menu_item -> {
                Navigation.findNavController(view!!).navigate(R.id.navigate_to_history_conversion)
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }
}