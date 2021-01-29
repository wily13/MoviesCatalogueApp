package com.example.moviescatalogueapp.api

import com.example.moviescatalogueapp.data.source.response.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    fun getAllMovies(
        @Query("api_key") api: String
    ): Call<MoviesResponse>

    @GET("tv/popular")
    fun getAllTvShows(
        @Query("api_key") api: String
    ): Call<MoviesResponse>

    @GET("movie/{movie_id}")
    fun getDetailMovies(
        @Path("movie_id") id: Int,
        @Query("api_key") api: String
    ): Call<MoviesObject>

    @GET("tv/{tv_id}")
    fun getDetailTvShows(
        @Path("tv_id") id: Int,
        @Query("api_key") api: String
    ): Call<MoviesObject>

    @GET("movie/{movie_id}/credits")
    fun getCreditsMovies(
        @Path("movie_id") id: Int,
        @Query("api_key") api: String
    ): Call<CreditsResponse>

    @GET("configuration/countries")
    fun getCountries(
        @Query("api_key") api: String
    ): Call<List<CountriesResponse>>

    @GET("configuration/languages")
    fun getLanguages(
        @Query("api_key") api: String
    ): Call<List<LanguageResponse>>

}