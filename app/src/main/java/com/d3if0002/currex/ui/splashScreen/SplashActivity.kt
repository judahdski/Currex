package com.d3if0002.currex.ui.splashScreen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.d3if0002.currex.R
import com.d3if0002.currex.db.ExchangeDB
import com.d3if0002.currex.model.ProgressIndicator
import com.d3if0002.currex.repository.RepositoryDB
import com.d3if0002.currex.repository.RepositoryDataStore
import com.d3if0002.currex.ui.MainActivity

class SplashActivity : AppCompatActivity() {

    private val viewModel: SplashViewModel by lazy {
        val db = ExchangeDB.getInstance(this)
        val factory = SplashViewModelFactory(RepositoryDB(db.dao), RepositoryDataStore(this))
        ViewModelProvider(this, factory)[SplashViewModel::class.java]
    }

    companion object {
        const val BASE_CURRENCY = "USD"
        const val BASE_COUNTRY = "usa"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()

        viewModel.getLatestRatesViewModel(BASE_CURRENCY)

        viewModel.status.observe(this) {
            updateProgress(it)
        }

        viewModel.rates.observe(this) {
            viewModel.insertData(it, BASE_CURRENCY, BASE_COUNTRY)
        }
    }

    private fun updateProgress(status: ProgressIndicator) {
        var progressStatus = false

        when (status) {
            ProgressIndicator.LOADING -> {
                viewModel.setApiDataStatus(ProgressIndicator.LOADING)
            }
            ProgressIndicator.SUCCESS -> {
                progressStatus = true
                viewModel.setApiDataStatus(ProgressIndicator.SUCCESS)
            }
            ProgressIndicator.FAILED -> {
                viewModel.setApiDataStatus(ProgressIndicator.FAILED)
            }
        }

        if (progressStatus) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}