package com.d3if0002.currex.ui.crypto

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.d3if0002.currex.repository.Repository

class CryptoViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CryptoViewModel(repository) as T
    }
}