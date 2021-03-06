package com.d3if0002.currex.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Conversion")
data class ConversionEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val baseCurr: String,
    val targetCurr: String,
    val amount: String,
    val result: String
)