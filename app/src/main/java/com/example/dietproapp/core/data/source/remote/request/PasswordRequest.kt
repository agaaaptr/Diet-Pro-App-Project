package com.example.dietproapp.core.data.source.remote.request

data class PasswordRequest(
    val email:      String,
    val password: String? = null,
    val token:   String? = null
)