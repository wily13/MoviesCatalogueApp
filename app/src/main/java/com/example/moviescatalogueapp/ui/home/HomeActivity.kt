package com.example.moviescatalogueapp.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moviescatalogueapp.R
import com.example.moviescatalogueapp.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var activityHomeBinding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityHomeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(activityHomeBinding.root)

        supportActionBar?.title = getString(R.string.title_home_movies)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        activityHomeBinding.apply {
            viewPager.adapter = sectionsPagerAdapter
            tabs.setupWithViewPager(viewPager)
        }
        supportActionBar?.elevation = 0f
    }
}