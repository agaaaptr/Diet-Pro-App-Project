package com.example.dietproapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.dietproapp.core.data.repository.AppRepository
import com.example.dietproapp.core.data.source.remote.request.LoginRequest

class LoginViewModel(val repo:AppRepository): ViewModel() {

    private val _text   =   MutableLiveData<String>().apply {
        value = "Hy Lord"
    }

    val text : LiveData<String> =   _text

    fun ubahData() {
        _text.postValue("aku user")
    }

    fun login(data: LoginRequest) =   repo.login(data).asLiveData()

}