package com.example.dietproapp.core.data.source.remote.response

import com.example.dietproapp.core.data.source.model.Laporan

data class LaporResponse (
    val code :  Int? = null,
    val message:    String? = null,
    val totalKalori: Int?,
    val data : List<Laporan>?

)
