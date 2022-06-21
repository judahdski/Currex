package com.d3if0002.currex.ui.crypto

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.d3if0002.currex.repository.RepositoryAPI

class CryptoViewModelFactory(private val RepositoryAPI: RepositoryAPI) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CryptoViewModel(RepositoryAPI) as T
    }
}