package com.example.dietproapp.core.data.source.remote.response

import com.example.dietproapp.core.data.source.model.Makanan

data class MakananResponse(

    val code :  Int? = null,
    val message:    String? = null,
    val data: List<Makanan>
)