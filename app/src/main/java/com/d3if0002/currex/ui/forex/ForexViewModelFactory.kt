package com.d3if0002.currex.ui.forex

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.d3if0002.currex.repository.RepositoryDB
import com.d3if0002.currex.repository.RepositoryDataStore

class ForexViewModelFactory(private val repoDB: RepositoryDB, private val repoDS: RepositoryDataStore) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ForexViewModel(repoDB, repoDS) as T
    }
}