package com.example.dietproapp.ui.notifications

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

class ReminderViewModel: ViewModel()
{
        var reminderItems = MutableLiveData<MutableList<ReminderItem>?>()

        init {
                reminderItems.value = mutableListOf()
        }

        fun addReminderItem(newTask: ReminderItem)
        {
                val list = reminderItems.value
                list!!.add(newTask)
                reminderItems.postValue(list)
        }

        fun updateReminderItem(id: UUID, name: String, desc: String, dueTime: LocalTime?)
        {
                val list = reminderItems.value
                val task = list!!.find { it.id == id }!!
                task.name = name
                task.desc = desc
                task.dueTime = dueTime
                reminderItems.postValue(list)
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun setCompleted(taskItem: ReminderItem)
        {
                val list = reminderItems.value
                val task = list!!.find { it.id == taskItem.id }!!
                if (task.completedDate == null)
                        task.completedDate = LocalDate.now()
                reminderItems.postValue(list)
        }
}