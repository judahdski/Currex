package com.d3if0002.currex.model

data class TimeSeriesData (
    val motd: Map<String, String>,
    val success: Boolean,
    val timeseries: Boolean,
    val base: String,
    val startDate: String,
    val endDate: String,
    val rates: Map<String, Map<String, Double>>,
)