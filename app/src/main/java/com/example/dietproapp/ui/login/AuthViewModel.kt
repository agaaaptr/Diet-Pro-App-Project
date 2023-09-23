package com.example.dietproapp.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.dietproapp.core.data.repository.AppRepository
import com.example.dietproapp.core.data.source.remote.request.LoginRequest
import com.example.dietproapp.core.data.source.remote.request.PasswordRequest
import com.example.dietproapp.core.data.source.remote.request.RegisterRequest
import com.example.dietproapp.core.data.source.remote.request.UpdateRequest
import okhttp3.MultipartBody

class AuthViewModel(val repo:AppRepository): ViewModel() {

    fun login(data: LoginRequest) =   repo.login(data).asLiveData()
    fun register(data: RegisterRequest) =   repo.register(data).asLiveData()
    fun updateUser(data: UpdateRequest) =   repo.updateUser(data).asLiveData()
    fun uploadUser(id: Int? = null, fileImage: MultipartBody.Part? = null) =   repo.uploadUser(id, fileImage).asLiveData()

    fun forgotPassword(data: PasswordRequest)   =   repo.forgotPassword(data).asLiveData()
    fun resetPassword(data: PasswordRequest)   =   repo.resetPassword(data).asLiveData()

}