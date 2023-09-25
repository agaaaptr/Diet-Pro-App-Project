package com.example.dietproapp.core.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class NotifResponse(
    val succes: String?,
    val message: String?,
    val data: List<Notif>?
)
@Parcelize
data class Notif(
    val title: String?,
    val body:   String?
) :Parcelable
