package com.example.dietproapp.ui.obslide.screen

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.dietproapp.NavigasiActivity
import com.example.dietproapp.core.data.source.remote.network.State
import com.example.dietproapp.core.data.source.remote.request.UpdateRequest
import com.example.dietproapp.databinding.ThirdScreenBinding
import com.example.dietproapp.ui.login.AuthViewModel
import com.example.dietproapp.util.SPrefs
import com.inyongtisto.myhelper.extension.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ThirdScreen : Fragment() {

    private val viewModel: AuthViewModel by viewModel()
    private var _binding: ThirdScreenBinding? = null
    private val binding get() = _binding!!

    private val items = listOf("tidak aktif",
        "latihan ringan",
        "latihan sedang",
        "aktif",
        "sangat aktif")

    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ThirdScreenBinding.inflate(inflater, container, false)
        val root = binding.root

        adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.ddAktifitas.setAdapter(adapter)

        binding.btnFinish.setOnClickListener {
            update()
            intentActivity(NavigasiActivity::class.java)
            onBoardingFinish()
        }

        return root
    }

    private fun onBoardingFinish() {
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished", true)
        editor.apply()
    }

    private fun update() {

        if (binding.ddAktifitas.isEmpty()) return

        val idUser = SPrefs.getUser()?.id
        val nama = SPrefs.getUser()?.nama
        val email = SPrefs.getUser()?.email
        val uname = SPrefs.getUser()?.username
        val bb = SPrefs.getUser()?.berat_badan
        val tb = SPrefs.getUser()?.tinggi_badan
        val usia = SPrefs.getUser()?.usia
        val jk = SPrefs.getUser()?.jenis_kelamin


        val body = UpdateRequest(
            idUser.int(),nama,email,uname,bb,tb,usia,jk, // jika tidak menggunakna library idUser ?: 0,
            binding.ddAktifitas.text.toString()
        )

        viewModel.updateUser(body).observe(this) {

            when (it.state) {
                State.SUCCESS -> {
                    toastSuccess("Terimakasih sudah melengkapi data " + it.data?.nama)
                }
                State.ERROR -> {
                    toastError(it.message ?: "Error")
                }
                State.LOADING -> {
//                    progress.show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
