package com.example.dietproapp.ui.obslide.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.dietproapp.R
import com.example.dietproapp.core.data.source.remote.network.State
import com.example.dietproapp.core.data.source.remote.request.UpdateRequest
import com.example.dietproapp.databinding.SecondScreenBinding
import com.example.dietproapp.ui.login.AuthViewModel
import com.example.dietproapp.util.SPrefs
import com.inyongtisto.myhelper.extension.int
import com.inyongtisto.myhelper.extension.isEmpty
import com.inyongtisto.myhelper.extension.toastError
import com.inyongtisto.myhelper.extension.toastSuccess
import org.koin.androidx.viewmodel.ext.android.viewModel

class SecondScreen : Fragment() {

    private val viewModel: AuthViewModel by viewModel()
    private var _binding: SecondScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SecondScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnNext.setOnClickListener {
            update()
            val viewPager = activity?.findViewById<ViewPager2>(R.id.obs_view_pager)
            viewPager?.currentItem = 2
        }
    }

    private fun update() {

        if (binding.edtBb.isEmpty()) return
        if (binding.edtTb.isEmpty()) return

        val idUser = SPrefs.getUser()?.id
        val nama = SPrefs.getUser()?.nama
        val email = SPrefs.getUser()?.email
        val uname = SPrefs.getUser()?.username

        val body = UpdateRequest(
            idUser.int(),nama,email,uname, // jika tidak menggunakna library idUser ?: 0,
            binding.edtBb.text.toString(),
            binding.edtTb.text.toString()
        )

        viewModel.updateUser(body).observe(this) {

            when (it.state) {
                State.SUCCESS -> {
                    toastSuccess("Berhasil menambahkan Tinggi & Berat Badan" + it.data?.nama)
                }
                State.ERROR -> {
                    toastError(it.message ?: "Error")
                }
                State.LOADING -> {

                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
