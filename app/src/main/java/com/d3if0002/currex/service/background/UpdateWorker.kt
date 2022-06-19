package com.d3if0002.currex.service.background

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class UpdateWorker(
    context: Context,
    workerParams: WorkerParameters
) :
    Worker(context, workerParams) {
    override fun doWork(): Result {
        Log.d("Debugss", "Background task berhasil dijalankan")

        // TODO: Create a notification

        return Result.success()
    }
}