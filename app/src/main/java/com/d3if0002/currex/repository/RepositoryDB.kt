package com.d3if0002.currex.repository

import com.d3if0002.currex.db.ConversionEntity
import com.d3if0002.currex.db.ExchangeDAO
import com.d3if0002.currex.db.RateEntity

class RepositoryDB(private val db: ExchangeDAO) {
    fun insertRateForexRepo(rate: RateEntity) = db.insertRate(rate)
    fun getAllRateDataInfoRepo() = db.getAllRates()

    fun insertConversionRepo(conversion: ConversionEntity) {
        db.insertConversionCurrency(conversion)
    }

    fun getAllConversionRepo() = db.getAllConversion()
}