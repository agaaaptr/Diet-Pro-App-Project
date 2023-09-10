package com.example.dietproapp.ui.obslide

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.dietproapp.databinding.FragmentOnBoardingBinding
import com.example.dietproapp.ui.obslide.screen.FirstScreen
import com.example.dietproapp.ui.obslide.screen.SecondScreen
import com.example.dietproapp.ui.obslide.screen.ThirdScreen

class OnBoardingFragment : AppCompatActivity() {

    private lateinit var binding: FragmentOnBoardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentList = arrayListOf<Fragment>(
            FirstScreen(),
            SecondScreen(),
            ThirdScreen(),
            // tambahkan fragment onboarding lainnya
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            supportFragmentManager,
            lifecycle
        )

        val viewPager = binding.obsViewPager
        viewPager.adapter = adapter

    }
}


