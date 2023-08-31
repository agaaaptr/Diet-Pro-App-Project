package com.example.dietproapp.core.data.source.remote.request

import com.example.dietproapp.core.data.source.model.Makanan
import com.example.dietproapp.core.data.source.model.User

data class LaporanMakananRequest(
    val id_user:     Int?,
    val id_makanan:   Int?,
    val jenis:   String?,
    val kalori: String?,
    val makanan: Makanan,
    val user: User
)