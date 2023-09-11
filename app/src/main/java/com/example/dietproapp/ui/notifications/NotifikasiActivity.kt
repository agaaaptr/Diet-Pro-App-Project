package com.example.dietproapp.ui.notifications

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.dietproapp.R


class NotifikasiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifikasi)

        Log.d("RESPON", "PESAN SINGKAT")
    }
}
