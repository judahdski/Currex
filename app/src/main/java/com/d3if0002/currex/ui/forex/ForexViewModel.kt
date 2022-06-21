package com.d3if0002.currex.ui.forex

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d3if0002.currex.model.ApiStatus
import com.d3if0002.currex.repository.RepositoryAPI
import kotlinx.coroutines.launch

/*
    note:
    - function name conventions:
        - API Req methods => <req. method><title>ViewModel  (exp. getLatestRates)
        - live data var => <title> (exp. latestRate)
        - live data getter => <req. method><live data var> (exp. getLatestRates)
 */

class ForexViewModel(private val repo: RepositoryAPI) : ViewModel() {

    private val rateFromApi: MutableLiveData<Map<String, Double>> = MutableLiveData()
    private val status: MutableLiveData<ApiStatus> = MutableLiveData()
    private val flags: MutableLiveData<List<String>> = MutableLiveData()

    fun getLatestRatesViewModel() {
        viewModelScope.launch {
            status.postValue(ApiStatus.LOADING)
            val symbolArr = arrayOf("USD", "IDR", "JPY")

            val flagList = mutableListOf<String>()
            symbolArr.forEach {
                val country = repo.getCountryInfoRepo(it)
                flagList.add(country.flags["png"] as String)
            }
            val responseRates = repo.getLatestRatesRepo("USD", "IDR", "JPY")

            if (responseRates.isSuccessful) {
                status.postValue(ApiStatus.SUCCESS)

                rateFromApi.value = responseRates.body()?.rates
                flags.value = flagList
            } else {
                status.postValue(ApiStatus.FAILED)
                val errorMsg = responseRates.errorBody()
            }
        }
    }

    val getStatus get() = status
    val getFlags get() = flags
    val getRates get() = rateFromApi
}