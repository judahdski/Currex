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
        Historical Rates
     */
    @GET("/{date}")
    suspend fun getHistoricalRatesService(
        @Path("date") date: String
    ) : Response<Rate>

    /*
        Supported Symbols
     */
    @GET("/symbols")
    suspend fun getSupportedSymbolsService() : Symbol

    /*
        Convert Currency
     */
    @GET("/convert")
    suspend fun convertCurrencyService(
        @Query("from") baseSymbol: String,
        @Query("to") toSymbol: String,
    ) : Response<ConvertedCurrency>

    /*
        EU Vat rates
     */
    @GET("/vat_rates")
    suspend fun getEUVatRatesService() : Response<EUVatRate>

    /*
        Time-series data
     */
    @GET("/timeseries")
    suspend fun getTimeSeriesDataService(
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String,
    ) : Response<TimeSeriesData>

    /*
        Fluctuation data
     */
    @GET("/fluctuation")
    suspend fun getFluctuationDataService(
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String,
    ) : Response<FluctuationData>
}