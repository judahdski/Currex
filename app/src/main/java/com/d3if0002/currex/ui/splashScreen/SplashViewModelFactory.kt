package com.d3if0002.currex.ui.splashScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.d3if0002.currex.repository.RepositoryAPI
import com.d3if0002.currex.repository.RepositoryDB

class SplashViewModelFactory(private val repoAPI: RepositoryAPI, private val repoDB: RepositoryDB) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SplashViewModel(repoAPI, repoDB) as T
    }
}