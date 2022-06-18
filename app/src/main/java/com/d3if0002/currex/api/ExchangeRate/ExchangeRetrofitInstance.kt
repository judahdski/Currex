package com.d3if0002.currex.api.ExchangeRate

import com.d3if0002.currex.const.Constant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ExchangeRetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constant.EXCHANGE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val service by lazy {
        retrofit.create(ExchangeRetrofitInstance::class.java)
    }
}