package com.example.dietproapp.core.data.source.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Laporan(
    val makanan: String?,
    val created_at: String?,
    val id: Int?,
    val id_user: Int?,
    val id_makanan: Int?,
    val updated_at: String?,
    val kalori: Int?,
    val jumlah: Int?,
    val jumlah_kalori: Int?,
    val makananModel: Makanan,
    val user: User,
): Parcelable
