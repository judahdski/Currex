package com.d3if0002.currex.service.api.exchangeRate

import com.d3if0002.currex.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/*
    This interface contains HTTP API request methods.

    note:
    - function name conventions:
        API req method => <request method><title>Service  (exp. getLatestRatesService)
 */

interface ExchangeRateInterface {
    /*
        Latest Rates
     */
    @GET("/latest")
    suspend fun getLatestRatesService(
        @Query("base") symbol: String
    ) : Response<Rate>

    /*
        Convert Currency
     */
    @GET("/convert")
    suspend fun convertCurrencyService(
        @Query("from") baseSymbol: String,
        @Query("to") toSymbol: String,
        @Query("amount") amount: Int,
    ) : Response<ConvertedCurrency>
}