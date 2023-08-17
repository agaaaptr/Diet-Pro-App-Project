package com.example.dietproapp.ui.obslide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dietproapp.databinding.FragmentOnBoardingBinding
import com.example.dietproapp.ui.obslide.screen.FirstScreen
import com.example.dietproapp.ui.obslide.screen.SecondScreen
import com.example.dietproapp.ui.obslide.screen.ThirdScreen

class OnBoardingFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentOnBoardingBinding.inflate(inflater, container, false)


        val fragmentList = arrayListOf(
            FirstScreen(),
            SecondScreen(),
            ThirdScreen()
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        binding.obsViewPager.adapter = adapter
        binding.dotsIndicator.attachTo(binding.obsViewPager)

        return binding.root
    }

}