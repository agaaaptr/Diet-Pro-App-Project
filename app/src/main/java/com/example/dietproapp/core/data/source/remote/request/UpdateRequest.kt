package com.example.dietproapp.core.data.source.remote.request

data class UpdateRequest(
    val id: Int,
    val nama:   String? = null,
    val email:      String? = null,
    val username:   String? = null,
    val berat_badan: String? = null,
    val tinggi_badan: String? = null,
    val usia: String? = null
)