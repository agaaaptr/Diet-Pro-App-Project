package com.example.dietproapp.ui.login

import android.os.Bundle
import com.example.dietproapp.core.data.source.remote.network.State
import com.example.dietproapp.core.data.source.remote.request.PasswordRequest
import com.example.dietproapp.databinding.ActivityForgotPasswordBinding
import com.example.dietproapp.ui.base.MyActivity
import com.google.firebase.auth.FirebaseAuth
import com.inyongtisto.myhelper.extension.dismisLoading
import com.inyongtisto.myhelper.extension.pushActivity
import com.inyongtisto.myhelper.extension.showLoading
import com.inyongtisto.myhelper.extension.showToast
import com.inyongtisto.myhelper.extension.toastError
import org.koin.androidx.viewmodel.ext.android.viewModel

class forgotPasswordActivity : MyActivity() {

    private val viewModel: AuthViewModel by viewModel()
    private var _binding: ActivityForgotPasswordBinding?  =   null
    private val binding get()   =   _binding!!
    private lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth    =   FirebaseAuth.getInstance()

        mainButton()
    }

    private fun mainButton() {
        binding.btnSend.setOnClickListener {
            reset()
        }
    }

    private fun reset() {

//        if (binding.edtEmail.isEmpty()) return

        val body = PasswordRequest(
            binding.edtEmail.text.toString()
        )


        viewModel.forgotPassword(body).observe(this) {

            when (it.state) {
                State.SUCCESS -> {
                    dismisLoading()
                    showToast("Token dikirim " + it.data?.nama)
//                    SPrefs.isLogin  =   true
//                    SPrefs.setUser(it.data)
                    pushActivity(LoginActivity::class.java)
                    finish()
                }
                State.ERROR -> {
                    dismisLoading()
                    toastError(it.message ?: "Error")
                }
                State.LOADING -> {
                    showLoading()
                }
            }

//            showToast("Selamat datang  " + it?.data?.nama)
        }
    }

}