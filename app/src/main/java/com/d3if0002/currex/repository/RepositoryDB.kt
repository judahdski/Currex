package com.d3if0002.currex.repository

import com.d3if0002.currex.db.RateDAO
import com.d3if0002.currex.db.RateEntity

class RepositoryDB(private val db: RateDAO) {
    fun insertRateForex(rate: RateEntity) = db.insertRate(rate)
    fun getAllRateDataInfo() = db.getAllRates()
}