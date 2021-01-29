package com.example.moviescatalogueapp.data.model

import com.example.moviescatalogueapp.data.source.response.CreatedByItem
import com.example.moviescatalogueapp.data.source.response.GenresItem

data class MoviesEntity(
    val moviesId: Int,
    val title: String,
    val posterPath: String,
    val overview: String,
    val originalLanguage: String,
    val genreIds: List<Int>,
    val releaseDate: String,
    val popularity: Double,
    val voteAverage: Double,
    val voteCount: Int,
    val budget: Int,
    val revenue: Int,
    val tagline: String,
    val status: String,
    val genres: List<GenresItem>,
    val name: String,
    val originCountry: List<String>,
    val firstAirDate: String,
    val lastAirDate: String,
    val type: String,
    val createdBy: List<CreatedByItem>
) {
    val baseUrl get() = "https://image.tmdb.org/t/p/w500"
}
