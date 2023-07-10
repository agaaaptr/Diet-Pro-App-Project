package com.example.dietproapp.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.dietproapp.NavigasiActivity
import com.example.dietproapp.core.data.source.remote.network.State
import com.example.dietproapp.core.data.source.remote.request.LoginRequest
import com.example.dietproapp.databinding.ActivityLoginBinding
import com.example.dietproapp.util.SPrefs
import com.inyongtisto.myhelper.extension.*
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

    private fun setData()   {
        binding.btnMasuk.setOnClickListener {
            login()
        }

    }

    private fun login()  {

        if (binding.edtEmail.isEmpty()) return
        if (binding.edtPassword.isEmpty(false))  return

        val body = LoginRequest(
            binding.edtEmail.text.toString(),
            binding.edtEmail.text.toString(),
            binding.edtPassword.text.toString()
        )

        viewModel.login(body).observe(  this) {

            when (it.state) {
                State.SUCCESS -> {
                    binding.pd.toGone()
                    showToast("Selamat datang " + it.data?.nama)
                    pushActivity(NavigasiActivity::class.java)
                }
                State.ERROR -> {
                    binding.pd.toGone()
                    toastError(it.message ?: "Error")
                }
                State.LOADING -> {
                    binding.pd.toVisible()
                }
            }

            showToast("Selamat datang  " + it?.data!!.nama)
        }
    }

//    fun testing() {
//        val s   =   SPrefs(this)
//        if(s.getIsLogin())  {
//            binding.tvDaftar.text = "SUDAH LOGIN"
//        } else
//            binding.tvDaftar.text =   "BELUM LOGIN"
//
//        binding.btnMasuk.setOnClickListener {
//            s.setIsLogin(true)
//        }
//
//        binding.btnMasuk.setOnClickListener {
//            s.setIsLogin(false)
//            onBackPressed()
//        }
//
//        Log.d("RESPON", "PESAN SINGKAT")
//    }

}