package com.d3if0002.currex.ui.forex

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.d3if0002.currex.db.RateEntity
import com.d3if0002.currex.repository.RepositoryDB

/*
    note:
    - function name conventions:
        - API Req methods => <req. method><title>ViewModel  (exp. getLatestRates)
        - live rates var => <title> (exp. latestRate)
        - live rates getter => <req. method><live rates var> (exp. getLatestRates)
 */

class ForexViewModel(private val repo: RepositoryDB) : ViewModel() {
    fun getRateList(): LiveData<List<RateEntity>> {
        return repo.getAllRateDataInfoRepo()
    }
}