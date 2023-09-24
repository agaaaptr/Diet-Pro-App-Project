package com.example.dietproapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.dietproapp.core.data.source.remote.network.State
import com.example.dietproapp.databinding.FragmentHomeBinding
import com.example.dietproapp.ui.home.adapter.HomeNewsAdapter
import com.example.dietproapp.ui.jurnalmakanan.MenuJurnalActivity
import com.example.dietproapp.ui.notifications.NotifikasiActivity
import com.example.dietproapp.ui.profil.ProfilActivity
import com.example.dietproapp.util.Constants
import com.example.dietproapp.util.SPrefs
import com.example.dietproapp.util.addOnItemClickListener
import com.inyongtisto.myhelper.extension.getInitial
import com.inyongtisto.myhelper.extension.intentActivity
import com.inyongtisto.myhelper.extension.logs
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    private val adapter = HomeNewsAdapter()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val homeViewModel =
//            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //Shimmer Effect
        binding.fl.visibility = View.GONE
        binding.shimmerView.stopShimmerAnimation()

        Handler().postDelayed({
            binding.fl.visibility   =   View.VISIBLE
            binding.shimmerView.stopShimmerAnimation()
            binding.shimmerView.visibility  =   View.GONE
        },5000)//seconds
        //after 5 seconds loading ui will be show

        swipeRefreshLayout = binding.swipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener {
            // Panggil fungsi untuk melakukan refresh data di sini
            refreshData()
        }

        setupAdapter()
        getnews()
        setUser()
        mainButton()

        return root
    }

    private fun refreshData() {
        // Sebagai contoh, kita akan memanggil kembali fungsi setUser()
        setUser()
        // Selesai melakukan refresh, beritahu SwipeRefreshLayout bahwa proses refresh sudah selesai
        swipeRefreshLayout.isRefreshing = false
    }

    private fun mainButton() {

        binding.rvListHomeNews.addOnItemClickListener { position, _ ->
            val selectedArticle = adapter.data[position]
            val intent = Intent(requireContext(), DetailHomeNewsActivity::class.java)
            intent.putExtra("article_url", selectedArticle.url)
            startActivity(intent)
        }

        binding.profile.setOnClickListener {
            intentActivity(ProfilActivity::class.java)
        }

        binding.menuJurnal.setOnClickListener {
            intentActivity(MenuJurnalActivity::class.java)
        }

        binding.imgJurnal.setOnClickListener {
            intentActivity(MenuJurnalActivity::class.java)
        }

        binding.imgNotif.setOnClickListener {
            intentActivity(NotifikasiActivity::class.java)
        }
    }

    private fun setUser() {
        val user = SPrefs.getUser()
        if (user != null) {
            binding.apply {
                tvUsername.text = user.nama
                tvTipeakun.text = user.role
                tvInisialProfil.text = user.nama.getInitial()

                val targetKalori = user.kebutuhan_kalori
//                tvTrackingKalori.text = targetKalori

                if (targetKalori != null) {
                    tvTrackingKalori.text = targetKalori
                } else {
                    tvTrackingKalori.text = "Data belum lengkap!"
                }

                val coba = 2000

                val targetKaloriInt = targetKalori?.toIntOrNull() ?: 0
                val currentProgress = if (targetKaloriInt != 0) {
                    coba
                } else {
                    0
                }

                progressBar.max = targetKaloriInt
                progressBar.progress = currentProgress

                Picasso.get().load(Constants.USER_URL + user.foto_profil).into(binding.imgProfil)
            }
        }
    }

    private fun setupAdapter() {
        binding.rvListHomeNews.layoutManager = LinearLayoutManager(requireContext())
        binding.rvListHomeNews.adapter = adapter
    }

    private fun getnews() {
        viewModel.getNews().observe(viewLifecycleOwner) { response ->
            when (response.state) {
                State.SUCCESS -> {
                    val newsResponse = response.data
                    if (newsResponse != null) {
                        // Check the status field in the NewsResponse
                        if (newsResponse.status == "ok") {
                            // The API request was successful and the status is "ok"
                            val articles = newsResponse.articles
//                            adapter.addItems(newsResponse)
                            if (articles != null) {

                                adapter.addItems(articles)
                                adapter.notifyDataSetChanged()

                            } else {

                            }
                        } else {
                            // Handle the case where the API response status is not "ok"
                            logs("Api", "API request failed with status: ${newsResponse.status}")
                        }
                    } else {
                        // Handle the case where the API response data is null
                        logs("Api", "API response data is null")
                    }
                }
                State.ERROR -> {
                    // Handle the error, show a message to the user, or log it
                    logs("Api", "API request failed")
                }
                State.LOADING -> {
                    // You can handle loading state here, e.g., show a progress indicator
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
