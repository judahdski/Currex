package com.d3if0002.currex.model

data class Rate(
    val motd: Map<String, String>,
    val success: Boolean,
    val base: String,
    val date: String,
    val rates: Map<String, Double>
)
