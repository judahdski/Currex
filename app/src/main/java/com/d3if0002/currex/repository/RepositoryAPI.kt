package com.d3if0002.currex.repository

import com.d3if0002.currex.service.api.ExchangeRateApi

class RepositoryAPI {

    suspend fun getLatestRatesRepo(baseCurrency: String) =
        ExchangeRateApi.service.getLatestRatesService(baseCurrency, 2)

    suspend fun convertCurrencyRepo(base: String, to: String, amount: Int) =
        ExchangeRateApi.service.convertCurrencyService(base, to, amount)

}