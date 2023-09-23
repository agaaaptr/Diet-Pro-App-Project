package com.example.dietproapp.core.data.source.remote.response

import com.example.dietproapp.core.data.source.model.password_reset_tokens

data class PasswordResponse (
    val code :  Int? = null,
    val message:    String? = null,
    val data: password_reset_tokens?  =   null
)
