package com.example.moviescatalogueapp.di

import com.example.moviescatalogueapp.repository.MoviesRepository

object Injection {
    fun provideRepository(): MoviesRepository {
        return MoviesRepository.getInstance()
    }
}