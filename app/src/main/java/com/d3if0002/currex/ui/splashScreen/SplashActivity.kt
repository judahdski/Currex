package com.d3if0002.currex.ui.splashScreen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.d3if0002.currex.R
import com.d3if0002.currex.db.ExchangeDB
import com.d3if0002.currex.model.ProgressIndicator
import com.d3if0002.currex.repository.RepositoryAPI
import com.d3if0002.currex.repository.RepositoryDB
import com.d3if0002.currex.ui.MainActivity

class SplashActivity : AppCompatActivity() {

    private val viewModel: SplashViewModel by lazy {
        val db = ExchangeDB.getInstance(this)
        val factory = SplashViewModelFactory(RepositoryAPI(), RepositoryDB(db.dao))
        ViewModelProvider(this, factory)[SplashViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()

        viewModel.getLatestRatesViewModel(this, "USD")

        viewModel.status.observe(this) {
            updateProgress(it)
        }

        viewModel.rates.observe(this) { rateList ->
            rateList.forEach {
                Log.d("DEBUGZZ", "${it.key}: $${it.value}")
            }
        }
    }

    private fun updateProgress(status: ProgressIndicator) {
        var progressStatus = false

        when (status) {
            ProgressIndicator.LOADING -> {
                Log.d("DEBUGZZ", "Lagi loading")
            }
            ProgressIndicator.SUCCESS -> {
                progressStatus = true
            }
            ProgressIndicator.FAILED -> {
                progressStatus = true
                Log.d("DEBUGZZ", "Gagal")
            }
        }

        if (progressStatus) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}