package com.example.moviescatalogueapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviescatalogueapp.data.model.*
import com.example.moviescatalogueapp.repository.MoviesRepository

class DetailMoviesViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    private var movieId : Int = 0

    fun setSelectedMovie(movieId: Int) {
        this.movieId = movieId
    }

    fun getMovieDetail(): LiveData<MoviesEntity> = moviesRepository.loadDetailMovies(movieId)

    fun getTvShowsDetail(): LiveData<MoviesEntity> = moviesRepository.loadDetailTvShows(movieId)

    fun getCreditsMovie(): LiveData<List<CrewEntity>> = moviesRepository.loadCreditsMovies(movieId)

    fun getCountries(countries: List<String>): LiveData<List<CountriesEntity>> = moviesRepository.loadCountries(countries)

    fun getLanguage(language: String): LiveData<List<LanguageEntity>> = moviesRepository.loadLanguage(language)
}