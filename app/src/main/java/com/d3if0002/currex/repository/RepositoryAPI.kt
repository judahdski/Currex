package com.d3if0002.currex.repository

import com.d3if0002.currex.service.api.exchangeRate.ExchangeRateApi
import com.d3if0002.currex.service.api.flags.FlagsApi

/*
    note:
    - function name conventions:
        repo method => <req. method><title>Repo  (exp. getLatestRatesRepo)
 */

class RepositoryAPI {

    suspend fun getLatestRatesRepo(baseCurrency: String) = ExchangeRateApi.service.getLatestRatesService(baseCurrency, 2)

    suspend fun convertCurrencyRepo(base: String, to: String, amount: Int) = ExchangeRateApi.service.convertCurrencyService(base, to, amount)

    suspend fun getCountryInfoRepo(
        currencyCode: String
    ) = FlagsApi.service.getCountryInfoService(currencyCode)
}