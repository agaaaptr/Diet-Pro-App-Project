package com.example.dietproapp.ui.profil

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.dietproapp.databinding.FragmentProfilBinding
import com.example.dietproapp.ui.editProfil.EditProfilActivity
import com.example.dietproapp.ui.login.LoginActivity
import com.example.dietproapp.util.Constants.USER_URL
import com.example.dietproapp.util.SPrefs
import com.inyongtisto.myhelper.extension.getInitial
import com.inyongtisto.myhelper.extension.intentActivity
import com.inyongtisto.myhelper.extension.pushActivity
import com.squareup.picasso.Picasso

class ProfilFragment : Fragment() {

    private lateinit var profilViewModel: ProfilViewModel
    private var _binding: FragmentProfilBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val profilViewModel =
            ViewModelProvider(this).get(ProfilViewModel::class.java)

        _binding = FragmentProfilBinding.inflate(inflater, container, false)
        val root: View = binding.root

        mainButton()
        return root
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

    }

    private fun setUser()   {
        val user = SPrefs.getUser()
        if (user != null) {
            binding.apply {
                tvNamaProfil.text = user.nama
                tvTipeakun.text  = user.email
                edtEmail.setText(user.email)
                edtUsername.setText(user.username)
                edtGender.setText(user.usia)
                edtHeight.setText(user.tinggi_badan)
                edtWeight.setText(user.berat_badan)
                tvInisialProfil.text    =   user.nama.getInitial()

                Picasso.get().load(USER_URL+user.foto_profil).into(binding.imgProfil)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}