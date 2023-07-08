package com.example.dietproapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dietproapp.core.data.repository.AppRepository

class LoginViewModel(val repo:AppRepository): ViewModel() {

    private val _text   =   MutableLiveData<String>().apply {
        value = "Hy Lord"
    }

    val text : LiveData<String> =   _text

    fun ubahData() {
        _text.postValue("aku user")
    }

}