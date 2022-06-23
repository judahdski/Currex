package com.d3if0002.currex.ui.splashScreen

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d3if0002.currex.db.RateEntity
import com.d3if0002.currex.model.ApiStatus
import com.d3if0002.currex.repository.RepositoryAPI
import com.d3if0002.currex.repository.RepositoryDB
import kotlinx.coroutines.launch

class SplashViewModel(private val repoAPI: RepositoryAPI, private val repoDB: RepositoryDB) :
    ViewModel() {
    private val _rates: MutableLiveData<Map<String, Double>> = MutableLiveData()
    private val _status: MutableLiveData<ApiStatus> = MutableLiveData()

    fun getLatestRatesViewModel(ctx: Context, baseCurrency: String) {
        viewModelScope.launch {
            _status.postValue(ApiStatus.LOADING)

            try {
                val response = repoAPI.getLatestRatesRepo(baseCurrency)

                if (response.isSuccessful) {
                    _status.postValue(ApiStatus.SUCCESS)
                    _rates.postValue(response.body()?.rates)
                } else {
                    _status.postValue(ApiStatus.FAILED)
                    Log.d("DEBUGZZ", "error response msg: ${response.errorBody().toString()}")
                    /*
                        TODO: save ke datastore klo statusnya failed, biar nanti di forex fragment
                        tinggal ambil statenya trus ubah viewnya
                     */
                }
            } catch (e: Exception) {
                _status.postValue(ApiStatus.FAILED)
                Log.d("DEBUGZZ", "exception: ${e.message.toString()}")
                // TODO: sama kek yg di atas
            }
        }
    }

    fun insertData(baseImg: String, targetImg: String, symbol: String, rate: String) {
        viewModelScope.launch {
            val rateEntity = RateEntity(
                baseImg = baseImg,
                targetImg = targetImg,
                symbol = symbol,
                rate = rate,
            )
            repoDB.insertRateForexRepo(rateEntity)
        }
    }

    val status get() = _status
    val rates get() = _rates
}