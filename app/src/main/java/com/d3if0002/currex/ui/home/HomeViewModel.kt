package com.d3if0002.currex.ui.home

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.d3if0002.currex.service.background.UpdateWorker
import java.util.concurrent.TimeUnit

class HomeViewModel : ViewModel() {
    fun schedulerUpdate(app: Application) {
        val request = OneTimeWorkRequestBuilder<UpdateWorker>()
            .setInitialDelay(1, TimeUnit.MINUTES)
            .build()

        WorkManager.getInstance(app).enqueueUniqueWork(
            "updater",
            ExistingWorkPolicy.REPLACE,
            request
        )
    }
}