package com.d3if0002.currex.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d3if0002.currex.model.ApiStatus
import com.d3if0002.currex.repository.RepositoryAPI
import kotlinx.coroutines.launch

class SplashViewModel(private val repo: RepositoryAPI) : ViewModel() {
    private val _rates: MutableLiveData<Map<String, Double>> = MutableLiveData()
    private val _status: MutableLiveData<ApiStatus> = MutableLiveData()
    private val _flags: MutableLiveData<List<String>> = MutableLiveData()

    fun getLatestRatesViewModel() {
        viewModelScope.launch {
            _status.postValue(ApiStatus.LOADING)
            val symbolArr = arrayOf("USD", "IDR", "JPY")

            val flagList = mutableListOf<String>()
            symbolArr.forEach {
                val country = repo.getCountryInfoRepo(it)
                flagList.add(country.flags["png"] as String)
            }
            val responseRates = repo.getLatestRatesRepo("USD", "IDR", "JPY")

            if (responseRates.isSuccessful) {
                _status.postValue(ApiStatus.SUCCESS)

                _rates.value = responseRates.body()?.rates
                _flags.value = flagList
            } else {
                _status.postValue(ApiStatus.FAILED)
                val errorMsg = responseRates.errorBody()
            }
        }
    }

    val status get() = _status
    val flags get() = _flags
    val rates get() = _rates
}