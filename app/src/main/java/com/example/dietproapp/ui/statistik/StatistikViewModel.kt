package com.example.dietproapp.ui.statistik

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StatistikViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Statistik Fragment"
    }
    val text: LiveData<String> = _text
}
