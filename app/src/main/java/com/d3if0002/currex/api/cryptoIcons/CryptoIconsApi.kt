package com.d3if0002.currex.api.cryptoIcons

import com.d3if0002.currex.const.Constant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CryptoIconsApi {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constant.ICONS_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val service by lazy {
        retrofit.create(CryptoIconsInterface::class.java)
    }
}