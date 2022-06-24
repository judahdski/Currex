package com.d3if0002.currex.ui.convertCurrency

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.d3if0002.currex.repository.RepositoryAPI
import com.d3if0002.currex.repository.RepositoryDB

class ConvertCurrencyViewModelFactory(private val repoAPI: RepositoryAPI, private val repoDB: RepositoryDB) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ConvertCurrencyViewModel(repoAPI, repoDB) as T
    }
}