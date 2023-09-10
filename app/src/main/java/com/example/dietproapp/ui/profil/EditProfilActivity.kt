package com.example.dietproapp.ui.profil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.dietproapp.R

class EditProfilActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val statusBarColor = ContextCompat.getColor(this, R.color.background)
        window.statusBarColor = statusBarColor
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profil)

        val lightStatusIcons = false // Set this to true for light icons, false for dark icons
    }
}