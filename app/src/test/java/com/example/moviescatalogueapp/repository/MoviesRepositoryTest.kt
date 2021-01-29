package com.example.moviescatalogueapp.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.moviescatalogueapp.utils.LiveDataTestUtil
import com.example.moviescatalogueapp.utils.MoviesDataDummy
import com.google.common.truth.Truth.assertThat
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MoviesRepositoryTest {

    private val moviesRepository = FakeMoviesRepository()

    private val moviesResponse = MoviesDataDummy.generateRemoteDummyMovies()
    private val moviesId = moviesResponse[0].moviesId
    private val tvShwosResponse = MoviesDataDummy.generateRemoteDummyTvShows()
    private val tvShowsId = tvShwosResponse[0].moviesId

    val originLanguage = moviesResponse[0].originalLanguage
    val originCounrty = tvShwosResponse[0].originCountry

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun loadAllMovies() {
        val moviesEntities = LiveDataTestUtil.getValue(moviesRepository.loadAllMovies())
        assertNotNull(moviesEntities)
        assertThat(moviesEntities.size.toLong()).isGreaterThan(moviesResponse.size.toLong())
        System.out.println(moviesEntities)
    }

    @Test
    fun loadAllTvShows() {
        val moviesEntities = LiveDataTestUtil.getValue(moviesRepository.loadAllTvShows())
        assertNotNull(moviesEntities)
        assertThat(moviesEntities.size.toLong()).isGreaterThan(tvShwosResponse.size.toLong())
        System.out.println(moviesEntities)
    }

    @Test
    fun loadDetailMovies() {
        val moviesEntities = LiveDataTestUtil.getValue(moviesRepository.loadDetailMovies(moviesId))
        assertNotNull(moviesEntities)
        System.out.println(moviesEntities)
    }

    @Test
    fun loadCreditsMovies() {
        val creditEntities = LiveDataTestUtil.getValue(moviesRepository.loadCreditsMovies(moviesId))
        assertNotNull(creditEntities)
        System.out.println(creditEntities)
    }

    @Test
    fun loadDetailTvShows() {
        val moviesEntities =
            LiveDataTestUtil.getValue(moviesRepository.loadDetailTvShows(tvShowsId))
        assertNotNull(moviesEntities)
        System.out.println(moviesEntities)
    }

    @Test
    fun loadCountries() {
        val countryEntity = LiveDataTestUtil.getValue(moviesRepository.loadCountries(originCounrty))
        assertNotNull(countryEntity)
        System.out.println(countryEntity)
    }

    @Test
    fun loadLanguage() {
        val languageEntity =
            LiveDataTestUtil.getValue(moviesRepository.loadLanguage(originLanguage))
        assertNotNull(languageEntity)
        System.out.println(languageEntity)
    }
}