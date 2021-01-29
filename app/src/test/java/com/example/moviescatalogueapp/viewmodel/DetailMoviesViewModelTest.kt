package com.example.moviescatalogueapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviescatalogueapp.data.model.CountriesEntity
import com.example.moviescatalogueapp.data.model.CrewEntity
import com.example.moviescatalogueapp.data.model.LanguageEntity
import com.example.moviescatalogueapp.data.model.MoviesEntity
import com.example.moviescatalogueapp.repository.MoviesRepository
import com.example.moviescatalogueapp.utils.MoviesDataDummy
import com.google.common.truth.Truth.assertThat
import org.junit.Test

import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailMoviesViewModelTest {

    private lateinit var viewModel: DetailMoviesViewModel
    private val dummyMovies = MoviesDataDummy.generateDummyMovies()[0]
    private val dummyTvShows = MoviesDataDummy.generateDummyTvShows()[0]
    private val dummyCrew = MoviesDataDummy.generateDummyCredits()
    private val dummyCountry = MoviesDataDummy.generateDummyCountry()
    private val dummyLanguage = MoviesDataDummy.generateDummyLanguage()
    private val moviesId = dummyMovies.moviesId
    private val tvshowId = dummyTvShows.moviesId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @Mock
    private lateinit var movieDetailObserver: Observer<MoviesEntity>

    @Mock
    private lateinit var tvDetailObserver: Observer<MoviesEntity>

    @Mock
    private lateinit var creditMovieObserver: Observer<List<CrewEntity>>

    @Mock
    private lateinit var countriesmovieObserver: Observer<List<CountriesEntity>>

    @Mock
    private lateinit var languagemovieObserver: Observer<List<LanguageEntity>>

    @Before
    fun setup(){
        viewModel = DetailMoviesViewModel(moviesRepository)
        viewModel.setSelectedMovie(moviesId)
    }


    @Test
    fun getMovieDetail() {
        val movies = MutableLiveData<MoviesEntity>()
        movies.value = dummyMovies

        `when`(moviesRepository.loadDetailMovies(moviesId)).thenReturn(movies)
        viewModel.setSelectedMovie(dummyMovies.moviesId)
        val moviesEntity = viewModel.getMovieDetail().value as MoviesEntity
        verify(moviesRepository).loadDetailMovies(moviesId)
        assertThat(moviesEntity).isNotNull()

        dummyMovies.apply {
            assertThat(moviesId).isEqualTo(moviesEntity.moviesId)
            assertThat(title).isEqualTo(moviesEntity.title)
            assertThat(posterPath).isEqualTo(moviesEntity.posterPath)
            assertThat(overview).isEqualTo(moviesEntity.overview)
            assertThat(releaseDate).isEqualTo(moviesEntity.releaseDate)
            assertThat(popularity).isEqualTo(moviesEntity.popularity)
            assertThat(voteAverage).isEqualTo(moviesEntity.voteAverage)
            assertThat(tagline).isEqualTo(moviesEntity.tagline)
            assertThat(type).isEqualTo(moviesEntity.type)
            assertThat(status).isEqualTo(moviesEntity.status)
            assertThat(originalLanguage).isEqualTo(moviesEntity.originalLanguage)
            assertThat(budget).isEqualTo(moviesEntity.budget)
            assertThat(revenue).isEqualTo(moviesEntity.revenue)
        }

        viewModel.getMovieDetail().observeForever(movieDetailObserver)
        verify(movieDetailObserver).onChanged(dummyMovies)
    }

    @Test
    fun getCreditsMovie() {
        val crew = MutableLiveData<List<CrewEntity>>()
        crew.value = dummyCrew

        `when`(moviesRepository.loadCreditsMovies(moviesId)).thenReturn(crew)
        viewModel.setSelectedMovie(dummyMovies.moviesId)
        val creEntity = viewModel.getCreditsMovie().value
        verify(moviesRepository).loadCreditsMovies(moviesId)
        assertThat(creEntity).isNotNull()

        viewModel.getCreditsMovie().observeForever(creditMovieObserver)
        verify(creditMovieObserver).onChanged(dummyCrew)
    }

    @Test
    fun getTvShowsDetail() {
        val tvShows = MutableLiveData<MoviesEntity>()
        tvShows.value = dummyTvShows

        `when`(moviesRepository.loadDetailTvShows(tvshowId)).thenReturn(tvShows)
        viewModel.setSelectedMovie(dummyTvShows.moviesId)
        val moviesEntity = viewModel.getTvShowsDetail().value as MoviesEntity
        verify(moviesRepository).loadDetailTvShows(tvshowId)
        assertThat(moviesEntity).isNotNull()

        dummyTvShows.apply {
            assertThat(moviesId).isEqualTo(moviesEntity.moviesId)
            assertThat(title).isEqualTo(moviesEntity.title)
            assertThat(posterPath).isEqualTo(moviesEntity.posterPath)
            assertThat(overview).isEqualTo(moviesEntity.overview)
            assertThat(releaseDate).isEqualTo(moviesEntity.releaseDate)
            assertThat(popularity).isEqualTo(moviesEntity.popularity)
            assertThat(voteAverage).isEqualTo(moviesEntity.voteAverage)
            assertThat(tagline).isEqualTo(moviesEntity.tagline)
            assertThat(type).isEqualTo(moviesEntity.type)
            assertThat(status).isEqualTo(moviesEntity.status)
            assertThat(originalLanguage).isEqualTo(moviesEntity.originalLanguage)
            assertThat(budget).isEqualTo(moviesEntity.budget)
            assertThat(revenue).isEqualTo(moviesEntity.revenue)
        }

        viewModel.getTvShowsDetail().observeForever(tvDetailObserver)
        verify(tvDetailObserver).onChanged(dummyTvShows)
    }

    @Test
    fun getCountries() {
        val country = MutableLiveData<List<CountriesEntity>>()
        val originCounrty = dummyTvShows.originCountry
        country.value = dummyCountry

        `when`(moviesRepository.loadCountries(originCounrty)).thenReturn(country)
        val countriesEntity = viewModel.getCountries(originCounrty).value
        verify(moviesRepository).loadCountries(originCounrty)
        assertThat(countriesEntity).isNotNull()

        viewModel.getCountries(originCounrty).observeForever(countriesmovieObserver)
        verify(countriesmovieObserver).onChanged(dummyCountry)
    }

    @Test
    fun getLanguage() {
        val language = MutableLiveData<List<LanguageEntity>>()
        val originLanguage = dummyMovies.originalLanguage
        language.value = dummyLanguage

        `when`(moviesRepository.loadLanguage(originLanguage)).thenReturn(language)
        val languageEntity = viewModel.getLanguage(originLanguage).value
        verify(moviesRepository).loadLanguage(originLanguage)
        assertThat(languageEntity).isNotNull()

        viewModel.getLanguage(originLanguage).observeForever(languagemovieObserver)
        verify(languagemovieObserver).onChanged(dummyLanguage)
    }
}
