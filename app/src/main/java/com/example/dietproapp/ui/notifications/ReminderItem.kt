package com.example.dietproapp.ui.notifications

import android.content.Context
import androidx.core.content.ContextCompat
import com.example.dietproapp.R
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID

class ReminderItem (
    var name: String,
    var desc: String,
    var dueTime: LocalTime?,
    var completedDate: LocalDate?,
    var id: UUID = UUID.randomUUID()
)
{
    fun isCompleted() = completedDate != null
    fun imageResource(): Int = if(isCompleted()) androidx.appcompat.R.drawable.btn_checkbox_unchecked_to_checked_mtrl_animation
    else androidx.appcompat.R.drawable.btn_checkbox_checked_to_unchecked_mtrl_animation
    fun imageColor(context: Context): Int = if(isCompleted()) purple(context) else black(context)

    private fun purple(context: Context) = ContextCompat.getColor(context, R.color.primary)
    private fun black(context: Context) = ContextCompat.getColor(context, R.color.black)
}