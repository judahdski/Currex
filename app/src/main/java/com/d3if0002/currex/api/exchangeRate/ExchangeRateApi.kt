package com.d3if0002.currex.api.exchangeRate

import com.d3if0002.currex.const.Constant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ExchangeRateApi {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constant.EXCHANGE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val service by lazy {
        retrofit.create(ExchangeRateApi::class.java)
    }
}