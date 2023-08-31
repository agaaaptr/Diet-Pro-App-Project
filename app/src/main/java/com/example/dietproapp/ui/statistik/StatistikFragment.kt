package com.example.dietproapp.ui.statistik

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.dietproapp.databinding.FragmentStatistikBinding

class StatistikFragment : Fragment() {

    private lateinit var viewModel: StatistikViewModel
    private var _binding: FragmentStatistikBinding? = null
    private val binding get() = _binding!!

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val statistikViewModel =
            ViewModelProvider(this).get(StatistikViewModel::class.java)

        _binding = FragmentStatistikBinding.inflate(inflater, container, false)
        val root: View = binding.root

        swipeRefreshLayout = binding.swipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener {
            // Panggil fungsi untuk melakukan refresh data di sini
            refreshData()
        }

        setUser()
        return root
    }

    private fun refreshData() {
        // Sebagai contoh, kita akan memanggil kembali fungsi setUser()
        setUser()
        // Selesai melakukan refresh, beritahu SwipeRefreshLayout bahwa proses refresh sudah selesai
        swipeRefreshLayout.isRefreshing = false
    }

    private fun setUser() {

        val statistikViewModel = ViewModelProvider(this).get(StatistikViewModel::class.java)

        val textView: TextView = binding.textStatistik
        statistikViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
