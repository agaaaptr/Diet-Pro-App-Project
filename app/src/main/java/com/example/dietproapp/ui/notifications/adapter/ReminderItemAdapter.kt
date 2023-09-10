package com.example.dietproapp.ui.notifications.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.dietproapp.databinding.ReminderItemCellBinding
import com.example.dietproapp.ui.notifications.ReminderItem
import com.example.dietproapp.ui.notifications.ReminderItemClickListener
import com.example.dietproapp.ui.notifications.ReminderItemViewHolder

class ReminderItemAdapter(
    private val reminderItems: List<ReminderItem>,
    private val clickListener: ReminderItemClickListener
): RecyclerView.Adapter<ReminderItemViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReminderItemViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = ReminderItemCellBinding.inflate(from, parent, false)
        return ReminderItemViewHolder(parent.context, binding, clickListener)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ReminderItemViewHolder, position: Int) {
        holder.bindReminderItem(reminderItems[position])
    }

    override fun getItemCount(): Int = reminderItems.size
}