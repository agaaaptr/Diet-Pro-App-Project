package com.example.dietproapp.ui.konsultasi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class KonsultasiViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This page is under development"
    }
    val text: LiveData<String> = _text
}