package com.example.dietproapp.util

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.chibatching.kotpref.KotprefModel

object SPrefs :KotprefModel() {

//    private var sp : SharedPreferences? = null
    var isLogin  by booleanPref(false)

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

}