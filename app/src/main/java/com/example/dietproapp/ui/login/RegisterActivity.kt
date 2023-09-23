package com.example.dietproapp.ui.login

import android.os.Bundle
import com.example.dietproapp.core.data.source.remote.network.State
import com.example.dietproapp.core.data.source.remote.request.RegisterRequest
import com.example.dietproapp.databinding.ActivityRegisterBinding
import com.example.dietproapp.ui.base.MyActivity
import com.example.dietproapp.ui.obslide.OnBoardingFragment
import com.inyongtisto.myhelper.extension.intentActivity
import com.inyongtisto.myhelper.extension.isEmpty
import com.inyongtisto.myhelper.extension.pushActivity
import com.inyongtisto.myhelper.extension.showToast
import com.inyongtisto.myhelper.extension.toastError
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : MyActivity() {
    private val viewModel: AuthViewModel by viewModel()

    private var _binding: ActivityRegisterBinding?  =   null
    private val binding get()   =   _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
                    showToast(" lanjut langkah berikutnya" + it.data?.nama)
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