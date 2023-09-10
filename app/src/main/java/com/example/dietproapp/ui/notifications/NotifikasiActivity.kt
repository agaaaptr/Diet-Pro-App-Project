package com.example.dietproapp.ui.notifications

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dietproapp.databinding.ActivityNotifikasiBinding
import com.example.dietproapp.ui.notifications.adapter.ReminderItemAdapter

class NotifikasiActivity : AppCompatActivity(),ReminderItemClickListener
{
    private lateinit var binding: ActivityNotifikasiBinding
    private lateinit var reminderViewModel: ReminderViewModel

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityNotifikasiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        reminderViewModel = ViewModelProvider(this).get(ReminderViewModel::class.java)
        binding.newTaskButton.setOnClickListener {
            ReminderFragment(null).show(supportFragmentManager, "newTaskTag")
        }
        setRecyclerView()
    }

    private fun setRecyclerView()
    {
        val mainActivity = this
        reminderViewModel.reminderItems.observe(this){
            binding.todoListRecyclerView.apply {
                layoutManager = LinearLayoutManager(applicationContext)
                adapter = it?.let { it1 -> ReminderItemAdapter(it1, mainActivity) }
            }
        }
    }

    override fun editReminderItem(reminderItem: ReminderItem) {
        ReminderFragment(reminderItem).show(supportFragmentManager,"newTaskTag")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun completeReminderItem(reminderItem: ReminderItem) {
        reminderViewModel.setCompleted(reminderItem)
    }
}






