package com.example.dietproapp.ui.jurnalmakanan

import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dietproapp.core.data.source.model.Makanan
import com.example.dietproapp.core.data.source.remote.network.State
import com.example.dietproapp.databinding.ActivityJurnalBinding
import com.example.dietproapp.ui.base.MyActivity
import com.example.dietproapp.ui.jurnalmakanan.adapter.MenuJurnalAdapter
import com.inyongtisto.myhelper.extension.intentActivity
import com.inyongtisto.myhelper.extension.logs
import com.inyongtisto.myhelper.extension.toJson
import org.koin.androidx.viewmodel.ext.android.viewModel

class JurnalActivity : MyActivity() {

    private var _binding: ActivityJurnalBinding? = null
    private val binding get() = _binding!!

    private lateinit var listJurnalMakanan: RecyclerView
    private lateinit var searchView: SearchView

    private val viewModel: JurnalMenuViewModel by viewModel()

    private val adapter = MenuJurnalAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityJurnalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        searchView = binding.searchView

        setupAdapter()
        getData()
        mainButton()
        setupSearchView()

    }

    private fun setupSearchView() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }
        })
    }

    private fun mainButton() {
        binding.imgBack.setOnClickListener {
            intentActivity(MenuJurnalActivity::class.java)
        }
    }


    private fun setupAdapter() {
        listJurnalMakanan = binding.rvListJurnalmakanan
        listJurnalMakanan.layoutManager = LinearLayoutManager(this)
        listJurnalMakanan.adapter = adapter
    }

    private fun getData() {
        viewModel.menuJurnal().observe(this) {
            when (it.state) {
                State.SUCCESS -> {
                    logs("jurnalMakanan:" + it.data.toJson())
                    val data: List<Makanan> = it.data ?: emptyList()
//                    val data = it.data?.makanan ?: listOf()
                    adapter.addItems(data)
//                    binding.tvError.isVisible = data.isEmpty()
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
