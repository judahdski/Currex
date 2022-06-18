package com.d3if0002.currex.repository

import com.d3if0002.currex.api.exchangeRate.ExchangeRateApi
import com.d3if0002.currex.api.flags.FlagsApi

/*
    note:
    - function name conventions:
        repo method => <req. method><title>Repo  (exp. getLatestRatesRepo)
 */

class Repository {

    suspend fun getLatestRatesRepo(symbol: String) = ExchangeRateApi.service.getLatestRatesService(symbol)

    suspend fun getSupportedSymbolsRepo() = ExchangeRateApi.service.getSupportedSymbolsService()

    suspend fun getHistoricalRatesRepo(date: String) = ExchangeRateApi.service.getHistoricalRatesService(date)

    suspend fun convertCurrencyRepo(base: String, to: String) = ExchangeRateApi.service.convertCurrencyService(base, to)

    suspend fun getEUVatRatesRepo() = ExchangeRateApi.service.getEUVatRatesService()

    suspend fun getTimeSeriesDataRepo(
        startDate: String, endDate: String
    ) = ExchangeRateApi.service.getTimeSeriesDataService(startDate, endDate)

    suspend fun getFluctuationDataRepo(
        startDate: String, endDate: String
    ) = ExchangeRateApi.service.getFluctuationDataService(startDate, endDate)

    suspend fun getCountryInfoRepo(
        countryCode: String
    ) = FlagsApi.service.getCountryInfoService(countryCode)
}