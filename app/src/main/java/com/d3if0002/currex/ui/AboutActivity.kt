package com.d3if0002.currex.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.d3if0002.currex.R
import com.d3if0002.currex.databinding.ActivityAboutBinding
import com.d3if0002.currex.utils.Constant.Companion.DONATE_URL
import com.d3if0002.currex.utils.Constant.Companion.MAIN_URL

class AboutActivity : AppCompatActivity(), View.OnClickListener {

    private var _binding: ActivityAboutBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setActionbarTitle()
        setUI()
    }

    private fun setActionbarTitle() {
        supportActionBar?.title = getString(R.string.tentang_kami)
    }

    private fun setUI() {
        val viewBtn = binding.viewButton
        val supportBtn = binding.supportButton

        viewBtn.setOnClickListener(this)
        supportBtn.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        var url = ""

        when (p0?.id) {
            R.id.view_button -> {
                url = MAIN_URL
            }
            R.id.support_button -> {
                url = DONATE_URL
            }
        }

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        this.startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}