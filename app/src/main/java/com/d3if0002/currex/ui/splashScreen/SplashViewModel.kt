package com.d3if0002.currex.ui.splashScreen

import android.content.Context
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d3if0002.currex.db.RateEntity
import com.d3if0002.currex.model.ApiStatus
import com.d3if0002.currex.repository.RepositoryAPI
import com.d3if0002.currex.repository.RepositoryDB
import com.d3if0002.currex.ui.MainActivity
import kotlinx.coroutines.launch

class SplashViewModel(private val repoAPI: RepositoryAPI, private val repoDB: RepositoryDB) :
    ViewModel() {
    private val _rates: MutableLiveData<Map<String, Double>> = MutableLiveData()
    private val _status: MutableLiveData<ApiStatus> = MutableLiveData()
    private val _flags: MutableLiveData<List<String>> = MutableLiveData()

    fun getLatestRatesViewModel(ctx: Context, baseCurrency: String): Boolean {
        viewModelScope.launch {
            _status.postValue(ApiStatus.LOADING)

            try {
                // TODO: ambil data dari API
                val response = repoAPI.getLatestRatesRepo(baseCurrency)

                // TODO: kalo berhasil masukin ke db
                if (response.isSuccessful) {
                    val rates = response.body()?.rates

                    // TODO: masukin ke db
                    rates?.forEach {
                        val countryCode = getCountryFlag(it.key)

                        val rateEntity = RateEntity(
                            baseImg = "https://countryflagsapi.com/png/$countryCode",
                            targetImg = "https://countryflagsapi.com/png/$countryCode",
                            symbol = "$baseCurrency - ${it.key}",
                            rate = "${it.value}",
                        )

                        repoDB.insertRateForex(rateEntity)
                    }

                    // TODO: kalo gagal ambil error msg nya
                } else {
                    // TODO: ubah recycler view jadi error text
                    _status.postValue(ApiStatus.FAILED)
                }
            } catch (e: Exception) {
                // TODO: ubah recycler view jadi error text
                _status.postValue(ApiStatus.FAILED)
            }
        }

        return true
    }

    private fun getCountryFlag(currencyCode: String): String {
        return "usa"
    }

    val status get() = _status
    val flags get() = _flags
    val rates get() = _rates
}