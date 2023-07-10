package com.example.dietproapp.core.data.source.remote

import com.example.dietproapp.core.data.source.remote.network.ApiConfig
import com.example.dietproapp.core.data.source.remote.network.ApiService
import com.example.dietproapp.core.data.source.remote.request.LoginRequest

class RemoteDataSource(private val api: ApiService) {

    suspend fun login(data: LoginRequest) = api.login(data)

}