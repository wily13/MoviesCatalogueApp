package com.example.moviescatalogueapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviescatalogueapp.data.model.MoviesEntity
import com.example.moviescatalogueapp.repository.MoviesRepository

class MoviesViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    fun getAllMovies(): LiveData<List<MoviesEntity>> = moviesRepository.loadAllMovies()
}

