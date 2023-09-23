package com.example.dietproapp.core.data.source.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val berat_badan: String?,
    val created_at: String?,
    val email: String?,
    val foto_profil: String?,
    val id: Int?,
    val kebutuhan_kalori: String?,
    val nama: String?,
    val role: String?,
    val tinggi_badan: String?,
    val updated_at: String?,
    val username: String?,
    val jenis_kelamin: String?,
    val lapor: Laporan?,
    val aktivitas: String?,
    val usia: String?,
    val makan: Makanan?,
    val token: String?
):Parcelable