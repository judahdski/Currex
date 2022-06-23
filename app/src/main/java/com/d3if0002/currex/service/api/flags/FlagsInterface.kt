package com.d3if0002.currex.service.api.flags

import com.d3if0002.currex.model.Country
import retrofit2.http.GET
import retrofit2.http.Path

interface FlagsInterface {
    @GET("currency/{currency_code}")
    suspend fun getCountryInfoService(
        @Path("currency_code") currCode: String
    ) : Country
}