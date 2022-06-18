package com.d3if0002.currex.model

data class Symbol(
    val motd: Map<String, String>,
    val success: String,
    val symbols: Map<String, Map<String, String>>,
)
