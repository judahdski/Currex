package com.d3if0002.currex.ui.convertCurrency

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.d3if0002.currex.R
import com.d3if0002.currex.databinding.FragmentConvertCurrencyBinding
import com.d3if0002.currex.model.ApiStatus
import com.d3if0002.currex.repository.RepositoryAPI
import com.google.android.material.textfield.TextInputLayout

class ConvertCurrencyFragment : Fragment(), View.OnClickListener {

    private val viewModel: ConvertCurrencyViewModel by lazy {
        val factory = ConvertCurrencyViewModelFactory(RepositoryAPI())
        ViewModelProvider(requireActivity(), factory)[ConvertCurrencyViewModel::class.java]
    }

    private var _binding: FragmentConvertCurrencyBinding? = null
    private val binding get() = _binding!!

    private lateinit var fromEditText: TextInputLayout
    private lateinit var toEditText: TextInputLayout
    private lateinit var amountEditText: TextInputLayout

    private lateinit var resultText: TextView
    private lateinit var progressBar: ProgressBar

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
        viewModel.result.observe(viewLifecycleOwner) {
            setResultText(it)
        }
        viewModel.status.observe(viewLifecycleOwner) {
            updateProgressBar(it)
        }
    }

    private fun setResultText(text: Any) {
        if (text is Double) {
            val target = toEditText.editText?.text.toString().uppercase()

            resultText.text = getString(R.string.result_convert, target, text)
        } else {
            resultText.text = text.toString()
        }
    }

    private fun updateProgressBar(status: ApiStatus) {
        when (status) {
            ApiStatus.LOADING -> {
                progressBar.visibility = View.VISIBLE
            }
            ApiStatus.SUCCESS -> {
                progressBar.visibility = View.GONE
            }
            ApiStatus.FAILED -> {
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
        }
    }
}