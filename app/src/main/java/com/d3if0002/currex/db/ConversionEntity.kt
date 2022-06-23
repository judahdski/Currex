package com.d3if0002.currex.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "Conversion")
data class ConversionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val baseCurr: String,
    val targetCurr: String,
    val result: String,
    val timeStamp: Date
)