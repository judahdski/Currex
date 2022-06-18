package com.d3if0002.currex.model

data class FluctuationData(
    val motd: Map<String, String>,
    val success: Boolean,
    val fluctuation: Boolean,
    val startDate: String,
    val endData: String,
    val rates: Map<String, Any>,
)
