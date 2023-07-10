package com.example.dietproapp.core.data.source.remote.response

import com.example.dietproapp.core.data.source.model.infoUser

data class LoginResponse (

    val code :  Int? = null,
    val message:    String? = null,
    val data: infoUser?  =   null

    )