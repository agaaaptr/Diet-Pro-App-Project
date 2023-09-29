package com.example.dietproapp.ui.home

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.webkit.WebView
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

        val toolbar = binding.toolbar
        val progressBar = binding.progressBar

        setSupportActionBar(toolbar)
        assert(supportActionBar != null)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)


        progressBar.max = 100

        //Shimmer Effect
//        binding.webView.visibility = View.GONE
//        binding.shimmerView.stopShimmerAnimation()
//
//        Handler().postDelayed({
//            binding.webView.visibility   =   View.VISIBLE
//            binding.shimmerView.stopShimmerAnimation()
//            binding.shimmerView.visibility  =   View.GONE
//        },5000)//seconds
        //after 5 seconds loading ui will be show

        showWebView()
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
    private fun showWebView() {
        var articleUrl = intent.getStringExtra("article_url")
        val progressBar =   binding.progressBar
        val webView = binding.webView

        webView.settings.loadsImagesAutomatically = true
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.settings.setSupportZoom(true)
        webView.settings.builtInZoomControls = true
        webView.settings.displayZoomControls = false
        webView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        webView.loadUrl(articleUrl!!)

        progressBar.progress = 0

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, newUrl: String): Boolean {
                view.loadUrl(newUrl)
                progressBar.progress = 0
                return true
            }

            override fun onPageStarted(view: WebView, urlStart: String, favicon: Bitmap?) {
                super.onPageStarted(view, urlStart, favicon)
                articleUrl = urlStart
                invalidateOptionsMenu()
            }

            override fun onPageFinished(view: WebView, urlPage: String) {
                super.onPageFinished(view, urlPage)
                progressBar.visibility = View.GONE
                invalidateOptionsMenu()
            }
        }
    }

}