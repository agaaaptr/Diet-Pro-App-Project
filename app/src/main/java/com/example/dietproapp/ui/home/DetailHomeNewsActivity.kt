package com.example.dietproapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
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

        //Shimmer Effect
        binding.webView.visibility = View.GONE
        binding.shimmerView.stopShimmerAnimation()

        Handler().postDelayed({
            binding.webView.visibility   =   View.VISIBLE
            binding.shimmerView.stopShimmerAnimation()
            binding.shimmerView.visibility  =   View.GONE
        },5000)//seconds
        //after 5 seconds loading ui will be show

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

        //Share URL
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