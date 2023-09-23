package com.example.dietproapp.ui.jurnalmakanan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.dietproapp.core.data.repository.AppRepository
import com.example.dietproapp.core.data.source.local.DummyData
import com.example.dietproapp.core.data.source.model.Makanan
import com.google.gson.JsonObject

class JurnalMenuViewModel(private val repo:AppRepository): ViewModel() {

    val listMakanan: LiveData<List<Makanan>> =   MutableLiveData<List<Makanan>>().apply {
        value = DummyData.listProduct
    }

    fun getmenuJurnal() =   repo.getmenuJurnal().asLiveData()
    fun menuJurnal() =   repo.menuJurnal().asLiveData()
    fun store(idUser: Int?, requestJson: JsonObject) = repo.store(idUser, requestJson).asLiveData()

}