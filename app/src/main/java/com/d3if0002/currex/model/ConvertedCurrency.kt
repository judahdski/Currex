package com.d3if0002.currex.model

data class ConvertedCurrency (
    val motd: Map<String, String>,
    val success: Boolean,
    val query: Map<String, Any>,
    val info: Map<String, Double>,
    val historical: Boolean,
    val date: String,
    val result: Double
)
