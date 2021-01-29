package com.example.moviescatalogueapp.data.source

import androidx.lifecycle.LiveData
import com.example.moviescatalogueapp.data.model.*

interface MoviesDataSource {

    fun loadAllMovies(): LiveData<List<MoviesEntity>>

    fun loadAllTvShows(): LiveData<List<MoviesEntity>>

    fun loadDetailMovies(moviesId: Int): LiveData<MoviesEntity>

    fun loadCreditsMovies(moviesId: Int): LiveData<List<CrewEntity>>

    fun loadDetailTvShows(moviesId: Int): LiveData<MoviesEntity>

    fun loadCountries(originCountry: List<String>): LiveData<List<CountriesEntity>>

    fun loadLanguage(language: String): LiveData<List<LanguageEntity>>

}