package com.example.dietproapp.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import androidx.core.content.ContextCompat
import com.example.dietproapp.R
import com.example.dietproapp.databinding.ActivityOnboardingBinding
import com.inyongtisto.myhelper.extension.intentActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class OnBoardingActivity : AppCompatActivity() {

    private var _binding: ActivityOnboardingBinding?  =   null
    private val binding get()   =   _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        title = "DietProApp"
        val window: Window = this@OnBoardingActivity.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = ContextCompat.getColor(this@OnBoardingActivity, R.color.primary)

        startButton()

    }

    private fun startButton() {
        binding.startBtn.setOnClickListener {
            intentActivity(LoginActivity::class.java)
        }
    }
}