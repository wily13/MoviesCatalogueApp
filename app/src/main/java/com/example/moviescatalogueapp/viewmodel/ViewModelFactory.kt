package com.example.moviescatalogueapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviescatalogueapp.repository.MoviesRepository
import com.example.moviescatalogueapp.di.Injection

class ViewModelFactory private constructor(private val mMoviesRepository: MoviesRepository) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository())
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(MoviesViewModel::class.java) -> {
                return MoviesViewModel(
                    mMoviesRepository
                ) as T
            }
            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> {
                return TvShowViewModel(
                    mMoviesRepository
                ) as T
            }
            modelClass.isAssignableFrom(DetailMoviesViewModel::class.java) -> {
                return DetailMoviesViewModel(
                    mMoviesRepository
                ) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}