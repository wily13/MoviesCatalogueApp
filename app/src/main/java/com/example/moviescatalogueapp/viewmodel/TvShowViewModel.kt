package com.example.moviescatalogueapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviescatalogueapp.data.model.MoviesEntity
import com.example.moviescatalogueapp.repository.MoviesRepository

class TvShowViewModel (private val moviesRepository: MoviesRepository) : ViewModel() {

    fun getAllTvShows(): LiveData<List<MoviesEntity>> = moviesRepository.loadAllTvShows()
}