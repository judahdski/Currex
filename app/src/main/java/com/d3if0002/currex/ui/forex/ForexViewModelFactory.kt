package com.d3if0002.currex.ui.forex

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.d3if0002.currex.repository.RepositoryDB

class ForexViewModelFactory(private val repo: RepositoryDB) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ForexViewModel(repo) as T
    }
}