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
class MoviesViewModelTest {

    private lateinit var viewModel: MoviesViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @Mock
    private lateinit var observer: Observer<List<MoviesEntity>>

    @Before
    fun setup(){
        viewModel = MoviesViewModel(moviesRepository)
    }

    @Test
    fun getAllMovies() {
        val dummyMovies = MoviesDataDummy.generateDummyMovies()
        val movies = MutableLiveData<List<MoviesEntity>>()
        movies.value = dummyMovies

        `when`(moviesRepository.loadAllMovies()).thenReturn(movies)
        val moviesEntities = viewModel.getAllMovies().value
        verify(moviesRepository).loadAllMovies()
        assertThat(moviesEntities).isNotNull()
        assertThat(moviesEntities?.size).isEqualTo(movies.value!!.size)

        viewModel.getAllMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }
}