package com.example.dietproapp.ui.jurnalmakanan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dietproapp.NavigasiActivity
import com.example.dietproapp.databinding.ActivityMenuJurnalBinding
import com.inyongtisto.myhelper.extension.intentActivity
import com.inyongtisto.myhelper.extension.pushActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class MenuJurnalActivity : AppCompatActivity() {

    private val viewModel: JurnalMenuViewModel by viewModel()

    private var _binding: ActivityMenuJurnalBinding?  =   null
    private val binding get()   =   _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMenuJurnalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainButton()
    }

//    override fun onResume() {
//        setUser()
//        super.onResume()
//    }

    private fun mainButton() {
        binding.clPagi.setOnClickListener {
            pushActivity(JurnalActivity::class.java)
        }

        binding.clSiang.setOnClickListener {
            intentActivity(JurnalActivity::class.java)
        }

        binding.clMalam.setOnClickListener {
            intentActivity(JurnalActivity::class.java)
        }

        binding.clCamilan.setOnClickListener {
            intentActivity(JurnalActivity::class.java)
        }

        binding.imgBack.setOnClickListener {
            intentActivity(NavigasiActivity::class.java)
        }

    }

//    private fun setUser()   {
//        val user = SPrefs.getUser()
//        if (user != null) {
//            binding.apply {
//                tvNamaProfil.text = user.nama
//                tvTipeakun.text  = user.email
//                edtEmail.setText(user.email)
//                edtUsername.setText(user.username)
//                edtGender.setText(user.jenis_kelamin)
//                edtHeight.setText(user.tinggi_badan)
//                edtWeight.setText(user.berat_badan)
//                tvInisialProfil.text    =   user.nama.getInitial()
//
//                Picasso.get().load(Constants.USER_URL +user.foto_profil).into(binding.imgProfil)
//            }
//        }
//    }

//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }

}