package com.example.dietproapp.core.data.repository

import com.example.dietproapp.core.data.source.local.LocalDataSource
import com.example.dietproapp.core.data.source.model.Makanan
import com.example.dietproapp.core.data.source.remote.RemoteDataSource
import com.example.dietproapp.core.data.source.remote.network.Resource
import com.example.dietproapp.core.data.source.remote.request.LoginRequest
import com.example.dietproapp.core.data.source.remote.request.RegisterRequest
import com.example.dietproapp.core.data.source.remote.request.UpdateRequest
import com.example.dietproapp.util.SPrefs
import com.inyongtisto.myhelper.extension.getErrorBody
import com.inyongtisto.myhelper.extension.logs
import com.inyongtisto.myhelper.extension.toJson
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody

class AppRepository(val local:LocalDataSource, val remote:RemoteDataSource) {

    fun login(data: LoginRequest) = flow {
        emit(Resource.loading(null))
        try {
            remote.login(data).let {
                if (it.isSuccessful) {
                    SPrefs.isLogin = true
                    val body = it.body()
                    val user = body?.data
                    logs("user:" + user.toJson())
                    SPrefs.setUser(user)
                    emit(Resource.success(user))
                    logs("success" + body.toString())
                } else {
                    emit(Resource.error(it.getErrorBody()?.message ?: "Error Default", null))
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
                    emit(Resource.error(it.getErrorBody()?.message
                        ?: "Error Default", null))
                    logs("Error: " + "keterangan error")
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(e.message ?: "Terjadi Kesalahan", null))
            logs("Error:" + e.message)
        }
    }

    fun updateUser(data: UpdateRequest ) =   flow {
        emit(Resource.loading(null))
        try {
            remote.updateUser(data).let {
                if (it.isSuccessful) {
                    val body    =   it.body()
                    val user    = body?.data
                    SPrefs.setUser(user) //untuk update data user terbaru
                    emit(Resource.success(user))
                } else {
                    emit(Resource.error(it.getErrorBody()?.message
                        ?: "Error Default", null))
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(e.message ?: "Terjadi Kesalahan", null))
        }
    }

    fun uploadUser(id: Int? = null, fileImage: MultipartBody.Part? = null) =   flow {
        emit(Resource.loading(null))
        try {
            remote.uploadUser(id, fileImage).let {
                if (it.isSuccessful) {
                    val body    =   it.body()
                    val user    = body?.data
                    SPrefs.setUser(user) //untuk update data user terbaru
                    emit(Resource.success(user))
                } else {
                    emit(Resource.error(it.getErrorBody()?.message
                        ?: "Error Default", null))
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(e.message ?: "Terjadi Kesalahan", null))
        }
    }

    fun getmenuJurnal() = flow {
        emit(Resource.loading(null))
        try {
            remote.getmenuJurnal().let {
                if (it.isSuccessful) {
                    val body = it.body()
                    val dataMakanan = body?.data
                    emit(Resource.success(dataMakanan))
                } else {
                    emit(Resource.error(it.getErrorBody()?.message?: "Error Default", null))
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(e.message?: "Terjadi Kesalahan", null))
           }
        }

    fun menuJurnal() = flow <Resource<List<Makanan>>>{
        emit(Resource.loading(null))
        try {
            remote.menuJurnal().let {
                if (it.isSuccessful) {
                    val body = it.body()
                    val data = body?.data
                    logs("data:" + data.toJson())
                    // untuk update data user terbaru
                    logs("success" + body.toString())
                    emit(Resource.success(data))
                } else {
                    emit(Resource.error(it.getErrorBody()?.message?: "Error Default", null))
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(e.message?: "Terjadi Kesalahan", null))
           }
        }

//    class   ErrorCustom (
//        val ok: Boolean,
//        val error_code: Int,
//        val description: String?    = null
//            )

}