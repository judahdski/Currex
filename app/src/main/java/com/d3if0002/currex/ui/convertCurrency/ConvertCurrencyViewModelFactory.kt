package com.d3if0002.currex.ui.convertCurrency

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.d3if0002.currex.repository.RepositoryAPI

class ConvertCurrencyViewModelFactory(private val repo: RepositoryAPI) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ConvertCurrencyViewModel(repo) as T
    }
}