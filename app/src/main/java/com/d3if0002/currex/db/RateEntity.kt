package com.d3if0002.currex.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "RateData")
data class RateEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val baseImg: String,
    val targetImg: String,
    val symbol: String,
    val rate: Double
)