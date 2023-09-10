package com.example.dietproapp.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.dietproapp.R
import com.example.dietproapp.core.data.source.remote.network.State
import com.example.dietproapp.core.data.source.remote.request.RegisterRequest
import com.example.dietproapp.databinding.ActivityRegisterBinding
import com.example.dietproapp.ui.obslide.OnBoardingFragment
import com.inyongtisto.myhelper.extension.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : AppCompatActivity() {
    private val viewModel: AuthViewModel by viewModel()

    private var _binding: ActivityRegisterBinding?  =   null
    private val binding get()   =   _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val statusBarColor = ContextCompat.getColor(this, R.color.background)
        window.statusBarColor = statusBarColor

        setData()
        mainButton()
    }

    private fun mainButton() {

        binding.btnMoveLogin.setOnClickListener {
            intentActivity(LoginActivity::class.java)
        }

    }

    private fun setData() {

        binding.btnDaftar.setOnClickListener {
            register()
        }

    }

    private fun register() {

        if (binding.edtName.isEmpty()) return
        if (binding.edtEmail.isEmpty()) return
        if (binding.edtUname.isEmpty()) return
        if (binding.edtPassword.isEmpty()) return

        val body = RegisterRequest(
            binding.edtName.text.toString(),
            binding.edtEmail.text.toString(),
            binding.edtUname.text.toString(),
            binding.edtPassword.text.toString()
        )

        viewModel.register(body).observe(this) {

            when (it.state) {
                State.SUCCESS -> {
//                    dismisLoading()
                    showToast("Selamat " + it.data?.nama + " lanjut langkah berikutnya")
                    pushActivity(OnBoardingFragment::class.java) //push ke halaman LoginActivity
                }
                State.ERROR -> {
//                    dismisLoading()
                    toastError(it.message ?: "Error")
                }
                State.LOADING -> {
//                    showLoading()
                }
            }

//            showToast("Selamat datang  " + it?.data?.nama)
        }
    }
}