package com.example.dietproapp.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
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

        startButton()

    }

    private fun startButton() {
        binding.startBtn.setOnClickListener {
            intentActivity(LoginActivity::class.java)
        }
    }
}