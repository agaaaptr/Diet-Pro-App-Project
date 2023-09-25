package com.example.dietproapp.core.data.source.remote.network

import com.example.dietproapp.core.data.source.model.JurnalMakanan
import com.example.dietproapp.core.data.source.remote.request.LoginRequest
import com.example.dietproapp.core.data.source.remote.request.PasswordRequest
import com.example.dietproapp.core.data.source.remote.request.RegisterRequest
import com.example.dietproapp.core.data.source.remote.request.UpdateRequest
import com.example.dietproapp.core.data.source.remote.response.BaseSingelResponse
import com.example.dietproapp.core.data.source.remote.response.LaporResponse
import com.example.dietproapp.core.data.source.remote.response.LoginResponse
import com.example.dietproapp.core.data.source.remote.response.MakananResponse
import com.example.dietproapp.core.data.source.remote.response.NewsResponse
import com.example.dietproapp.core.data.source.remote.response.NotifResponse
import com.example.dietproapp.core.data.source.remote.response.PasswordResponse
import com.google.gson.JsonObject
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    //  @Header(API)
    @POST("login")
    suspend fun login(
      @Body login: LoginRequest,
    ): Response<LoginResponse>

    //  https://127.0.0.1:8000/api/register
    @POST("register")
    suspend fun register    (
        @Body data: RegisterRequest
    ):  Response<LoginResponse>

    @PUT("update-user/{id}")
    suspend fun updateUser    (
        @Path("id") int: Int,
        @Body data: UpdateRequest
    ):  Response<LoginResponse>

    @Multipart
    @POST("upload-user/{id}")
    suspend fun uploadUser    (
        @Path("id") int: Int? = null,
        @Body data: MultipartBody.Part? = null
    ):  Response<LoginResponse>

    @POST("jurnal-makanan")
    suspend fun menuJurnal   (
    ):  Response<MakananResponse>

    @POST("store/{id}")
    suspend fun store(
        @Path("id") id: Int?,
        @Body requestJson: JsonObject
    ): Response<LaporResponse>

    //ini ga dipake
    @GET("jurnalMakanan")
    suspend fun getmenuJurnal   (
    ):  Response<BaseSingelResponse<JurnalMakanan>>

    @POST("forgot-password")
    fun forgotPassword(
        @Path("email") string: String,
        @Body data: PasswordRequest
    ): Response<LoginResponse> // ApiResponse adalah kelas yang Anda buat untuk menangani respons API

    @POST("reset-password")
    fun resetPassword(
        @Body data : PasswordRequest
    ): Response<PasswordResponse>

    //Health News Api
    @GET("news")
    suspend fun getNews(
    ): Response<NewsResponse>

    @POST("push")
    suspend fun pushNotif(
    ): Response<NotifResponse>

}