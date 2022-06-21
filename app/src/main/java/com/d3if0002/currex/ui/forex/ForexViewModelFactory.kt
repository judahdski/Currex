package com.d3if0002.currex.ui.forex

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.d3if0002.currex.repository.RepositoryAPI

class ForexViewModelFactory(private val RepositoryAPI: RepositoryAPI) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ForexViewModel(RepositoryAPI) as T
    }
}