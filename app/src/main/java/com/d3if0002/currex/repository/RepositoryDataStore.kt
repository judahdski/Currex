package com.d3if0002.currex.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.d3if0002.currex.model.ProgressIndicator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "apistatus")
val API_STATUS = booleanPreferencesKey("api_status")

class RepositoryDataStore(val context: Context) {
    suspend fun setApiDataStatus(status: ProgressIndicator) {
        context.dataStore.edit { apistatus ->
            apistatus[API_STATUS] = when (status) {
                ProgressIndicator.LOADING -> true
                ProgressIndicator.SUCCESS -> false
                ProgressIndicator.FAILED -> true
            }
        }
    }

    val apiDataStatus: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[API_STATUS] ?: false
        }
}