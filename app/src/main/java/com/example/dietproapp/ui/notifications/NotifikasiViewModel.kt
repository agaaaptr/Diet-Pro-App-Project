package com.example.dietproapp.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.dietproapp.core.data.repository.AppRepository
import com.example.dietproapp.core.data.source.local.DummyData
import com.example.dietproapp.core.data.source.model.Makanan
import com.google.gson.JsonObject

class NotifikasiViewModel(private val repo:AppRepository): ViewModel() {

    fun pushNotif() = repo.pushNotif().asLiveData()


}