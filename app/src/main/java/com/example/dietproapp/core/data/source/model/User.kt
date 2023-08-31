package com.example.dietproapp.core.data.source.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
data class Makanan(
    val Nama_Bahan: String?,
    val created_at: String?,
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val updated_at: String?,
    val Ukuran_Porsi: String?,
    val Takaran: String?,
    val Energi_kkal: String?,
    val Protein_g: String?,
    val Lemak_g: String?,
    val KH_g: String?,
    val Serat_Total_g: String?,
    val Natrium_mg: String?,
    val Kalium_mg: String?,
    val Gula_Total_g: String?,
    val Jenis: String?
)

@Entity
data class User(
    val berat_badan: String?,
    val created_at: String?,
    val email: String?,
    val foto_profil: String?,
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val kebutuhan_kalori: String?,
    val nama: String?,
    val role: String?,
    val tinggi_badan: String?,
    val updated_at: String?,
    val username: String?,
    val jenis_kelamin: String?,
    val lapor: Laporan?,
    val aktivitas: String?,
    val usia: String?
)

@Entity(foreignKeys = [
    ForeignKey(entity = Makanan::class, parentColumns = ["id"], childColumns = ["id_makanan"]),
    ForeignKey(entity = User::class, parentColumns = ["id"], childColumns = ["id_user"])
])
data class Laporan(
    val jenis: String?,
    val created_at: String?,
    @PrimaryKey val id: Int?,
    val id_user: Int?,
    val id_makanan: Int?,
    val updated_at: String?,
    val kalori: String?,
//    val makanan: Makanan,
//    val user: User
)
