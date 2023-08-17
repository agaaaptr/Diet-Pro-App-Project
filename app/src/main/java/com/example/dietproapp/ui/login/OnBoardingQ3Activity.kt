package com.example.dietproapp.ui.login

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.dietproapp.R
import com.example.dietproapp.databinding.ActivityOnBoardingQ3Binding

class OnBoardingQ3Activity : AppCompatActivity() {

    private lateinit var binding: ActivityOnBoardingQ3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingQ3Binding.inflate(layoutInflater)
        setContentView(binding.root)


        val items = listOf("Tidak aktif (tidur, duduk): BMR x 1.2", "Sedikit aktif (aktivitas ringan/sedang 1-3 hari/minggu): BMR x 1.375", "Aktif (aktivitas sedang 3-5 hari/minggu): BMR x 1.55", "Sangat aktif (aktivitas berat 6-7 hari/minggu): BMR x 1.725", "Super aktif (aktivitas berat setiap hari atau pekerjaan fisik berat): BMR x 1.9")
        val adapter = ArrayAdapter(this, R.layout.list_item, items)
        binding.ddAktifitas.setAdapter(adapter)
    }
}