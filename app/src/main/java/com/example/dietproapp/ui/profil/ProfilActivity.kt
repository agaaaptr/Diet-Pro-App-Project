package com.example.dietproapp.ui.profil

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dietproapp.NavigasiActivity
import com.example.dietproapp.databinding.ActivityProfilBinding
import com.example.dietproapp.ui.editProfil.EditProfilActivity
import com.example.dietproapp.ui.login.LoginActivity
import com.example.dietproapp.util.Constants
import com.example.dietproapp.util.SPrefs
import com.inyongtisto.myhelper.extension.getInitial
import com.inyongtisto.myhelper.extension.intentActivity
import com.inyongtisto.myhelper.extension.pushActivity
import com.squareup.picasso.Picasso

class ProfilActivity : AppCompatActivity() {

//    private val viewModel: AuthViewModel by viewModel()

    private var _binding: ActivityProfilBinding?  =   null
    private val binding get()   =   _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityProfilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainButton()
    }

    override fun onResume() {
        setUser()
        super.onResume()
    }

    private fun mainButton() {
        binding.btnKeluar.setOnClickListener {
            SPrefs.isLogin  =   false
            pushActivity(LoginActivity::class.java)
        }

        binding.imgEdit.setOnClickListener {
            intentActivity(EditProfilActivity::class.java)
        }

        binding.imgBack.setOnClickListener {
            intentActivity(NavigasiActivity::class.java)
        }

    }

    private fun setUser()   {
        val user = SPrefs.getUser()
        if (user != null) {
            binding.apply {
                tvNamaProfil.text = user.nama
                tvTipeakun.text  = user.email
                edtEmail.setText(user.email)
                edtUsername.setText(user.username)
                edtGender.setText(user.jenis_kelamin)
                edtHeight.setText(user.tinggi_badan)
                edtWeight.setText(user.berat_badan)
                tvInisialProfil.text    =   user.nama.getInitial()

                Picasso.get().load(Constants.USER_URL +user.foto_profil).into(binding.imgProfil)
            }
        }
    }

//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }

}