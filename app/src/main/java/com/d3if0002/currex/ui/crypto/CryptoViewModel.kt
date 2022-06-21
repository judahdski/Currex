package com.d3if0002.currex.ui.crypto

import androidx.lifecycle.ViewModel
import com.d3if0002.currex.repository.RepositoryAPI

/*
    note:
    - function name conventions:
        - API Req methods => <req. method><title>ViewModel  (exp. getLatestRates)
        - live data var => <title> (exp. latestRate)
        - live data getter => <req. method><live data var> (exp. getLatestRates)
 */

class CryptoViewModel(private val repo: RepositoryAPI) : ViewModel() {
}