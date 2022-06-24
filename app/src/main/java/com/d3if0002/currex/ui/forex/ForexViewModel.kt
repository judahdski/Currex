package com.d3if0002.currex.ui.forex

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.d3if0002.currex.db.RateEntity
import com.d3if0002.currex.repository.RepositoryDB
import com.d3if0002.currex.repository.RepositoryDataStore

class ForexViewModel(private val repo: RepositoryDB, private val repoDS: RepositoryDataStore) :
    ViewModel() {
    fun getRateList(): LiveData<List<RateEntity>> {
        return repo.getAllRateDataInfoRepo()
    }

    val apiDataStatus get() = repoDS.apiDataStatus.asLiveData()
}