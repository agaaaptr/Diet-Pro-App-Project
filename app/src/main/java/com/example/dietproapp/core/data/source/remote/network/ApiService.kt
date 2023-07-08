package com.example.dietproapp.core.data.source.remote.network

import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.POST

interface ApiService {

    //  @Header(API)
    @POST("login")
    suspend fun login(
        //@Body user: Useer
    ): Response<RequestBody>


}