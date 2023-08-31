package com.example.dietproapp.ui.statistik

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dietproapp.core.data.source.model.Laporan
import com.example.dietproapp.core.data.source.model.User
import com.example.dietproapp.core.data.source.remote.response.LoginResponse

class StatistikViewModel : ViewModel() {

    private val _userData = MutableLiveData<User>()
    val userData: LiveData<User> get() = _userData

    private val _laporData = MutableLiveData<Laporan>()
    val laporData: LiveData<Laporan> get() = _laporData

    // Fungsi untuk mengisi data dari LoginResponse
    fun setData(loginResponse: LoginResponse) {
        _userData.value = loginResponse.data!!
        _laporData.value = loginResponse.lapor!!
    }
}
