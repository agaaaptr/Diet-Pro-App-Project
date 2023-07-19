package com.example.dietproapp.ui.profil

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.dietproapp.databinding.FragmentProfilBinding
import com.example.dietproapp.ui.login.LoginActivity
import com.example.dietproapp.util.SPrefs
import com.inyongtisto.myhelper.extension.pushActivity

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

        setUser()
        mainButton()
        return root
    }

    fun mainButton() {
        binding.btnKeluar.setOnClickListener {
            SPrefs.isLogin  =   false
            pushActivity(LoginActivity::class.java)
        }
    }

    private fun setUser()   {
        val user = SPrefs.getUser()
        if (user != null) {
            binding.apply {
                tvNamaProfil.text = user.nama
                tvEmailProfil.text  = user.email
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}