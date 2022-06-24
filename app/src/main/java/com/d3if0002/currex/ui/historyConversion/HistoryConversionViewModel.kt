package com.d3if0002.currex.ui.historyConversion

import androidx.lifecycle.ViewModel
import com.d3if0002.currex.repository.RepositoryDB

class HistoryConversionViewModel(repoDB: RepositoryDB) : ViewModel() {
    val conversionList = repoDB.getAllConversionRepo()
}
