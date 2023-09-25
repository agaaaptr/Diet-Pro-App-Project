package com.example.dietproapp.ui.notifications

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dietproapp.R
import com.example.dietproapp.core.data.source.model.Makanan
import com.example.dietproapp.core.data.source.remote.network.State
import com.example.dietproapp.core.data.source.remote.response.Notif
import com.example.dietproapp.databinding.ActivityJurnalBinding
import com.example.dietproapp.databinding.ActivityNotifikasiBinding
import com.example.dietproapp.ui.base.MyActivity
import com.example.dietproapp.ui.home.HomeViewModel
import com.example.dietproapp.ui.jurnalmakanan.adapter.MenuJurnalAdapter
import com.example.dietproapp.ui.notifications.adapter.NotifikasiAdapter
import com.inyongtisto.myhelper.extension.logs
import com.inyongtisto.myhelper.extension.toJson
import org.koin.androidx.viewmodel.ext.android.viewModel


class NotifikasiActivity : MyActivity() {

    private val viewModel: NotifikasiViewModel by viewModel()

    private var _binding: ActivityNotifikasiBinding? = null
    private val binding get() = _binding!!

    private lateinit var listNotif: RecyclerView
    private val adapter = NotifikasiAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityNotifikasiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAdapter()
        pushNotif()
    }

    private fun setupAdapter() {
        listNotif = binding.notifListRV
        listNotif.layoutManager = LinearLayoutManager(this)
        listNotif.adapter = adapter
    }

    private fun pushNotif() {
        viewModel.pushNotif().observe(this) {
            when (it.state) {
                State.SUCCESS -> {
                    logs("Notifikasi:" + it.data.toJson())
//                    val data: List<Notif> = it.data ?: emptyList()
                    val data = it.data?.data ?: listOf()
                    adapter.addItems(data)
                }
                State.ERROR -> {
                    binding.tvError.isVisible = true
                }
                State.LOADING -> {
                    // Tindakan yang perlu dilakukan saat loading
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
