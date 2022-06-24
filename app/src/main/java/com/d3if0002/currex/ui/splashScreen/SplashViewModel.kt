package com.d3if0002.currex.ui.splashScreen

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d3if0002.currex.db.RateEntity
import com.d3if0002.currex.model.ProgressIndicator
import com.d3if0002.currex.repository.RepositoryAPI
import com.d3if0002.currex.repository.RepositoryDB
import com.d3if0002.currex.repository.RepositoryDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SplashViewModel(private val repoDB: RepositoryDB, private val repoDS: RepositoryDataStore) :
    ViewModel() {

    private val repoAPI: RepositoryAPI = RepositoryAPI()

    private val _rates: MutableLiveData<Map<String, Double>> = MutableLiveData()
    private val _status: MutableLiveData<ProgressIndicator> = MutableLiveData()

    fun getLatestRatesViewModel(baseCurrency: String) {
        viewModelScope.launch {
            _status.postValue(ProgressIndicator.LOADING)

            try {
                val response = repoAPI.getLatestRatesRepo(baseCurrency)

                if (response.isSuccessful) {
                    _status.postValue(ProgressIndicator.SUCCESS)
                    _rates.postValue(response.body()?.rates)
                } else {
                    _status.postValue(ProgressIndicator.FAILED)
                }
            } catch (e: Exception) {
                _status.postValue(ProgressIndicator.FAILED)
            }
        }
    }

    fun insertData(data: Map<String, Double>, baseCurrency: String, baseCountry: String) {
        val listData: MutableList<RateEntity> = mutableListOf()

        data.forEach {
            val rateEntity = RateEntity(
                baseImg = "https://countryflagsapi.com/png/$baseCountry",
                targetImg = "",
                symbol = "$baseCurrency - ${it.key}",
                rate = it.value,
            )
            listData.add(rateEntity)
        }

        viewModelScope.launch(Dispatchers.IO) {
            try {
                repoDB.insertRateForexRepo(listData)
            } catch (e: Exception) {
                Log.d("DEBUGZZ", "$e")
            }
        }
    }

    fun setApiDataStatus(status: ProgressIndicator) {
        viewModelScope.launch(Dispatchers.IO) {
            repoDS.setApiDataStatus(status)
        }
    }

    val status get() = _status
    val rates get() = _rates
}