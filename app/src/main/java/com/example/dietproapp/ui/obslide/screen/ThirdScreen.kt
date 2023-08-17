package com.example.dietproapp.ui.obslide.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.dietproapp.R
import com.example.dietproapp.databinding.ThirdScreenBinding

class ThirdScreen : Fragment() {

    private var _binding: ThirdScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ThirdScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvFinish.setOnClickListener {
            val viewPager = activity?.findViewById<ViewPager2>(R.id.obs_view_pager)
            viewPager?.currentItem = 3
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
