package com.d3if0002.currex.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RateDAO {
    @Insert
    fun insert(rate: RateEntity)

    @Query("SELECT * FROM RateData ORDER BY id ASC LIMIT 15")
    fun getAllRates(): LiveData<List<RateEntity>>
}