package com.example.dietproapp.ui.notifications.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dietproapp.core.data.source.remote.response.Notif
import com.example.dietproapp.core.data.source.remote.response.NotifResponse
import com.example.dietproapp.databinding.NotificationBinding

@SuppressLint("NotifyDataSetChanged")
class NotifikasiAdapter : RecyclerView.Adapter<NotifikasiAdapter.ViewHolder>() {

    var data = ArrayList<Notif>()

    inner class ViewHolder(val itemBinding: NotificationBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: Notif, position: Int) {
            itemBinding.apply {
                title.text = item.title
                message.text = item.body
            }
        }
    }

    fun addItems(items: List<Notif>) {
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            NotificationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], position)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
