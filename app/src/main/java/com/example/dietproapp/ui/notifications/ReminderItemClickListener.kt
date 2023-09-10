package com.example.dietproapp.ui.notifications

interface ReminderItemClickListener {
    fun editReminderItem(reminderItem: ReminderItem)
    fun completeReminderItem(reminderItem: ReminderItem)
}