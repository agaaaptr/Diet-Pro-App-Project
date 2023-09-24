package com.example.dietproapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.dietproapp.databinding.ActivityDetailHomeNewsBinding
import com.inyongtisto.myhelper.extension.intentActivity

class DetailHomeNewsActivity : AppCompatActivity() {

    private var _binding: ActivityDetailHomeNewsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailHomeNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        val articleUrl = intent.getStringExtra("article_url")

        val webView = binding.webView
        webView.apply {
            webViewClient = WebViewClient()
            if (articleUrl != null) {
                loadUrl(articleUrl)
            }
        }

        mainButton()
    }

    private fun mainButton() {
        binding.toolbar.setOnClickListener {
            intentActivity(HomeFragment::class.java)
        }

        binding.imgShare.setOnClickListener {
            val articleUrl = intent.getStringExtra("article_url")
            val share = Intent(Intent.ACTION_SEND)
            share.type = "text/plain"
            share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET)
            share.putExtra(Intent.EXTRA_TEXT, articleUrl)
            startActivity(Intent.createChooser(share, "Bagikan ke : "))
        }
    }
}