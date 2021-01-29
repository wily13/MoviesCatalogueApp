package com.example.moviescatalogueapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
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
class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @Mock
    private lateinit var observer: Observer<List<MoviesEntity>>

    @Before
    fun setup(){
        viewModel = TvShowViewModel(moviesRepository)
    }

    @Test
    fun getAllTvShows() {
        val dummyMovies = MoviesDataDummy.generateDummyTvShows()
        val tvShows = MutableLiveData<List<MoviesEntity>>()
        tvShows.value = dummyMovies

        `when`(moviesRepository.loadAllTvShows()).thenReturn(tvShows)
        val moviesEntities = viewModel.getAllTvShows().value
        verify(moviesRepository).loadAllTvShows()
        assertThat(moviesEntities).isNotNull()
        assertThat(moviesEntities?.size).isEqualTo(tvShows.value!!.size)

        viewModel.getAllTvShows().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }
}