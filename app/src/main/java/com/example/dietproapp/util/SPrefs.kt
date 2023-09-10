package com.example.dietproapp.util

import com.chibatching.kotpref.KotprefModel
import com.example.dietproapp.core.data.source.model.User
import com.inyongtisto.myhelper.extension.toJson
import com.inyongtisto.myhelper.extension.toModel

object SPrefs :KotprefModel() {

    //    private var sp : SharedPreferences? = null
    var isLogin by booleanPref(false)
    var user by stringPref()
    var token by stringPref("token")

    fun setUser(data: User?) {
        user = data.toJson()
    }

    fun getUser(): User? {
        if (user.isEmpty()) return null
        return user.toModel(User::class.java)
    }
}

    fun getMakan() = SPrefs.getUser()?.makan?.id

//    init {
//        sp  =   activity.getSharedPreferences("MYPREF", Context.MODE_PRIVATE)
//    }
//
//    fun  setIsLogin(value: Boolean)   {
//        sp!!.edit().putBoolean(login, value).apply()
//    }
//
//    fun getIsLogin() :Boolean {
//        return sp!!.getBoolean(login, false)
//    }
