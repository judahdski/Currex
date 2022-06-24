package com.d3if0002.currex.ui.historyConversion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d3if0002.currex.db.ConversionEntity
import com.d3if0002.currex.model.ProgressIndicator
import com.d3if0002.currex.repository.RepositoryDB
import kotlinx.coroutines.launch
import java.lang.Exception

class HistoryConversionViewModel(repoDB: RepositoryDB) : ViewModel() {
    val conversionList = repoDB.getAllConversionRepo()
}
