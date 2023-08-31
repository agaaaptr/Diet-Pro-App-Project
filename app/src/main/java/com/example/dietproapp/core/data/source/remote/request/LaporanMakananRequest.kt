package com.example.dietproapp.core.data.source.remote.request

data class LaporanMakananRequest(
    val id: Int,
    val id_user:     Int,
    val id_makanan:   Int,
    val jenis:   String,
    val kalori: String,
    val makanan: String,
    val user: String
)