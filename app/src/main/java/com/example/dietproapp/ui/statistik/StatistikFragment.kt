package com.example.dietproapp.ui.statistik

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import com.example.dietproapp.R

class StatistikFragment : Fragment() {

    companion object {
        fun newInstance() = StatistikFragment()
    }

    private lateinit var viewModel: StatistikViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val statusBarColor = ContextCompat.getColor(requireContext(), R.color.background)
        activity?.window?.statusBarColor = statusBarColor
        val rootView = inflater.inflate(R.layout.fragment_statistik, container, false)

        // Set the status bar icons to black (light status bar)
        activity?.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        return inflater.inflate(R.layout.fragment_statistik, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(StatistikViewModel::class.java)
        // TODO: Use the ViewModel
    }


}