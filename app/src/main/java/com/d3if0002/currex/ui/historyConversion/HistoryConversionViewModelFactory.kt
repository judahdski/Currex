package com.d3if0002.currex.ui.historyConversion

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.d3if0002.currex.repository.RepositoryDB

class HistoryConversionViewModelFactory(private val repositoryDB: RepositoryDB) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HistoryConversionViewModel(repositoryDB) as T
    }
}