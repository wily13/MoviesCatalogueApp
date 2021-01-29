package com.example.moviescatalogueapp.utils

import com.example.moviescatalogueapp.data.model.MoviesEntity
import com.example.moviescatalogueapp.data.source.response.MoviesObject

object MappingHelper {

    fun mapFromEntityMovies(entity: MoviesObject): MoviesEntity {
        return MoviesEntity(
            moviesId = entity.moviesId,
            title = entity.title,
            posterPath = entity.posterPath,
            overview = entity.overview,
            originalLanguage = entity.originalLanguage,
            genreIds = entity.genreIds,
            releaseDate = entity.releaseDate,
            popularity = entity.popularity,
            voteAverage = entity.voteAverage,
            voteCount = entity.voteCount,
            budget = 0,
            revenue = 0,
            tagline = "",
            status = "",
            genres = emptyList(),
            name = "",
            originCountry = emptyList(),
            firstAirDate = "",
            lastAirDate = "",
            type = "",
            createdBy = emptyList()
        )
    }

    fun mapFromEntityTvShows(entity: MoviesObject): MoviesEntity {
        return MoviesEntity(
            moviesId = entity.moviesId,
            title = "",
            posterPath = entity.posterPath,
            overview = entity.overview,
            originalLanguage = entity.originalLanguage,
            genreIds = entity.genreIds,
            releaseDate = "",
            popularity = entity.popularity,
            voteAverage = entity.voteAverage,
            voteCount = entity.voteCount,
            budget = 0,
            revenue = 0,
            tagline = "",
            status = "",
            genres = emptyList(),
            name = entity.name,
            originCountry = entity.originCountry,
            firstAirDate = entity.firstAirDate,
            lastAirDate = "",
            type = "",
            createdBy = emptyList()
        )
    }


    fun mapToEntityMovies(entity: MoviesObject): MoviesEntity {
        return MoviesEntity(
            moviesId = entity.moviesId,
            title = entity.title,
            posterPath = entity.posterPath,
            overview = entity.overview,
            originalLanguage = entity.originalLanguage,
            genreIds = emptyList(),
            releaseDate = entity.releaseDate,
            popularity = entity.popularity,
            voteAverage = entity.voteAverage,
            voteCount = entity.voteCount,
            budget = entity.budget,
            revenue = entity.revenue,
            tagline = entity.tagline,
            status = entity.status,
            genres = entity.genres,
            name = "",
            originCountry = emptyList(),
            firstAirDate = "",
            lastAirDate = "",
            type = "",
            createdBy = emptyList()
        )
    }

    fun mapToEntityTvShows(entity: MoviesObject): MoviesEntity {
        return MoviesEntity(
            moviesId = entity.moviesId,
            title = "",
            posterPath = entity.posterPath,
            overview = entity.overview,
            originalLanguage = entity.originalLanguage,
            genreIds = emptyList(),
            releaseDate = "",
            popularity = entity.popularity,
            voteAverage = entity.voteAverage,
            voteCount = entity.voteCount,
            budget = 0,
            revenue = 0,
            tagline = entity.tagline,
            status = entity.status,
            genres = entity.genres,
            name = entity.name,
            originCountry = entity.originCountry,
            firstAirDate = entity.firstAirDate,
            lastAirDate = entity.lastAirDate,
            type = entity.type,
            createdBy = entity.createdBy
        )
    }

    fun moviesMapFromEntityList(entities: ArrayList<MoviesObject>): ArrayList<MoviesEntity> {
        return entities.map { mapFromEntityMovies(it) } as ArrayList<MoviesEntity>
    }

    fun tvShowsMapFromEntityList(entities: ArrayList<MoviesObject>): ArrayList<MoviesEntity> {
        return entities.map { mapFromEntityTvShows(it) } as ArrayList<MoviesEntity>
    }

}