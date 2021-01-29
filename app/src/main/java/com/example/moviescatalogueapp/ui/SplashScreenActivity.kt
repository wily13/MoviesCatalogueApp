package com.example.moviescatalogueapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.moviescatalogueapp.R
import com.example.moviescatalogueapp.ui.home.HomeActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler(mainLooper).postDelayed({
            val intent = Intent(this@SplashScreenActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }, 1800)
    }
}