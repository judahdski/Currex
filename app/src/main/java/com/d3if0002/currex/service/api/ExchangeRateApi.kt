package com.d3if0002.currex.service.api

import com.d3if0002.currex.utils.Constant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ExchangeRateApi {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constant.EXCHANGE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val service: ExchangeRateInterface by lazy {
        retrofit.create(ExchangeRateInterface::class.java)
    }
}