package com.d3if0002.currex.ui.splashScreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.d3if0002.currex.R
import com.d3if0002.currex.db.RateDB
import com.d3if0002.currex.repository.RepositoryAPI
import com.d3if0002.currex.repository.RepositoryDB
import com.d3if0002.currex.ui.MainActivity

class SplashActivity : AppCompatActivity() {

    private val viewModel: SplashViewModel by lazy {
        val db = RateDB.getInstance(this)
        val factory = SplashViewModelFactory(RepositoryAPI(), RepositoryDB(db.dao))
        ViewModelProvider(this, factory)[SplashViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        },3000)
    }
}