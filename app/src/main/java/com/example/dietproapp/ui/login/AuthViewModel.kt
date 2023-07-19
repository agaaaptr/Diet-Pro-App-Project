package com.example.dietproapp.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.dietproapp.core.data.repository.AppRepository
import com.example.dietproapp.core.data.source.remote.request.LoginRequest
import com.example.dietproapp.core.data.source.remote.request.RegisterRequest

class AuthViewModel(val repo:AppRepository): ViewModel() {

    fun login(data: LoginRequest) =   repo.login(data).asLiveData()
    fun register(data: RegisterRequest) =   repo.register(data).asLiveData()


}