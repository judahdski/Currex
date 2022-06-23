package com.d3if0002.currex.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ExchangeDAO {
    @Insert
    fun insertRate(rate: RateEntity)

    @Query("SELECT * FROM RateData ORDER BY rate DESC LIMIT 15")
    fun getAllRates(): LiveData<List<RateEntity>>


    @Insert
    fun insertConversionCurrency(conversion: ConversionEntity)

    @Query("SELECT * FROM Conversion ORDER BY id DESC")
    fun getAllConversion(): LiveData<List<ConversionEntity>>
}