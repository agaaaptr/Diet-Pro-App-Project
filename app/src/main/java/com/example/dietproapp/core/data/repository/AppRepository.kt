package com.example.dietproapp.core.data.repository

import com.example.dietproapp.core.data.source.local.LocalDataSource
import com.example.dietproapp.core.data.source.remote.RemoteDataSource
import com.example.dietproapp.core.data.source.remote.network.Resource
import com.example.dietproapp.core.data.source.remote.request.LoginRequest
import com.example.dietproapp.core.data.source.remote.request.RegisterRequest
import com.example.dietproapp.util.SPrefs
import com.inyongtisto.myhelper.extension.getErrorBody
import com.inyongtisto.myhelper.extension.logs
import kotlinx.coroutines.flow.flow

class AppRepository(val local:LocalDataSource, val remote:RemoteDataSource) {

    fun login(data: LoginRequest) =   flow {
        emit(Resource.loading(null))
        try {
            remote.login(data).let {
                if (it.isSuccessful) {
                    SPrefs.isLogin  = true
                    val body    =   it.body()
                    val user    = body?.data
                    SPrefs.setUser(user)
                    emit(Resource.success(user))
                    logs("success" + body.toString())
                } else {
                    emit(Resource.error(it.getErrorBody(ErrorCustom::class.java)?.description
                        ?: "Error Default", null))
                    logs("Error: " + "keterangan error")
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(e.message ?: "Terjadi Kesalahan", null))
            logs("Error:" + e.message)
        }
    }

    fun register(data: RegisterRequest) =   flow {
        emit(Resource.loading(null))
        try {
            remote.register(data).let {
                if (it.isSuccessful) {
                    SPrefs.isLogin  = true //jika mau ke login dulu ini bisa dihapus
                    val body    =   it.body()
                    val user    = body?.data
                    SPrefs.setUser(user) //ini juga -> lanjut ke RegisterActivity
                    emit(Resource.success(user))
                    logs("success" + body.toString())
                } else {
                    emit(Resource.error(it.getErrorBody(ErrorCustom::class.java)?.description
                        ?: "Error Default", null))
                    logs("Error: " + "keterangan error")
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(e.message ?: "Terjadi Kesalahan", null))
            logs("Error:" + e.message)
        }
    }

    class   ErrorCustom (
        val ok: Boolean,
        val error_code: Int,
        val description: String?    = null
            )

}