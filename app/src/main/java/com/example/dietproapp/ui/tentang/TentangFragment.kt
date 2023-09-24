package com.example.dietproapp.ui.tentang

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dietproapp.R

class TentangFragment : Fragment() {

    companion object {
        fun newInstance() = TentangFragment()
    }

    private lateinit var viewModel: TentangViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tentang, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TentangViewModel::class.java)
        // TODO: Use the ViewModel
    }

}