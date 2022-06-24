package com.d3if0002.currex.ui.convertCurrency

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d3if0002.currex.db.ConversionEntity
import com.d3if0002.currex.model.ProgressIndicator
import com.d3if0002.currex.repository.RepositoryAPI
import com.d3if0002.currex.repository.RepositoryDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*
    note:
    - function name conventions:
        - API Req methods => <req. method><title>ViewModel  (exp. getLatestRates)
        - live rates var => <title> (exp. latestRate)
        - live rates getter => <req. method><live rates var> (exp. getLatestRates)
 */

class ConvertCurrencyViewModel(private val repoAPI: RepositoryAPI, private val repoDB: RepositoryDB) : ViewModel() {
    private val _result: MutableLiveData<Any> = MutableLiveData()
    private val _status: MutableLiveData<ProgressIndicator> = MutableLiveData()

    fun convertCurrency(base: String, target: String, amount: Int) {
        viewModelScope.launch {
            _status.postValue(ProgressIndicator.LOADING)
            val response = repoAPI.convertCurrencyRepo(base, target, amount)

            if (response.isSuccessful) {
                _status.postValue(ProgressIndicator.SUCCESS)
                _result.postValue(response.body()?.result)
            } else {
                _status.postValue(ProgressIndicator.FAILED)
                _result.postValue(response.errorBody())
            }
        }
    }

    fun insertConversionViewModel(base: String, target: String, amount: Int, result: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            val conversion = ConversionEntity(
                baseCurr = base,
                targetCurr = target,
                amount = amount.toString(),
                result = result.toString(),
            )
            repoDB.insertConversionRepo(conversion)
        }
    }

    val result get() = _result
    val status get() = _status
}