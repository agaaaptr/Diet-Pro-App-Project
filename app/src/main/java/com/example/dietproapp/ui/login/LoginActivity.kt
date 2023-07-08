package com.example.dietproapp.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.dietproapp.databinding.ActivityLoginBinding
import com.example.dietproapp.util.SPrefs
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val viewModel   :   LoginViewModel  by  viewModel()

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setData()

    }

        fun setData()   {
        viewModel.text.observe(this, {
            binding.edtEmail.setText(it)
        })

        binding.btnMasuk.setOnClickListener {
            viewModel.ubahData()
        }

    }

    fun testing() {
        val s   =   SPrefs(this)
        if(s.getIsLogin())  {
            binding.tvDaftar.text = "SUDAH LOGIN"
        } else
            binding.tvDaftar.text =   "BELUM LOGIN"

        binding.btnMasuk.setOnClickListener {
            s.setIsLogin(true)
        }

        binding.btnMasuk.setOnClickListener {
            s.setIsLogin(false)
            onBackPressed()
        }

        Log.d("RESPON", "PESAN SINGKAT")
    }

}