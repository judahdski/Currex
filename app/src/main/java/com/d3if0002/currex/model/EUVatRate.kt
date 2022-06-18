package com.d3if0002.currex.model

data class EUVatRate (
    val motd: Map<String, String>,
    val success: String,
    val rates: Map<String, Any>
)
