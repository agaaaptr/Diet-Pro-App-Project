package com.example.dietproapp.core.data.source.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Makanan(
    val Energi_kkal: String?  = null,
    val Gula_Total_g: String? = null,
    val Jenis: String? = null,
    val KH_g: String? = null,
    val Kalium_mg: String? = null,
    val Lemak_g: String? = null,
    val Nama_Bahan: String? = null,
    val Natrium_mg: String? = null,
    val Protein_g: String? = null,
    val Serat_Total_g: String? = null,
    val Takaran: String? = null,
    val Ukuran_Porsi: String? = null,
    val created_at: String? = null,
    val id: Int,
    val updated_at: String? = null,
) : Parcelable