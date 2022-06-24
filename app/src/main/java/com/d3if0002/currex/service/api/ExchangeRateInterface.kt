package com.d3if0002.currex.service.api

import com.d3if0002.currex.model.ConvertedCurrency
import com.d3if0002.currex.model.Rate
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ExchangeRateInterface {
    /*
        Latest Rates
     */
    @GET("/latest")
    suspend fun getLatestRatesService(
        @Query("base") baseCurrency: String,
        @Query("places") places: Int,
    ): Response<Rate>

    /*
        Convert Currency
     */
    @GET("/convert")
    suspend fun convertCurrencyService(
        @Query("from") baseSymbol: String,
        @Query("to") toSymbol: String,
        @Query("amount") amount: Int,
    ): Response<ConvertedCurrency>
}