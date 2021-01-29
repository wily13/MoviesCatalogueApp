package com.example.moviescatalogueapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviescatalogueapp.BuildConfig
import com.example.moviescatalogueapp.api.ApiConfig
import com.example.moviescatalogueapp.data.model.*
import com.example.moviescatalogueapp.data.source.MoviesDataSource
import com.example.moviescatalogueapp.data.source.response.*
import com.example.moviescatalogueapp.utils.MappingHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FakeMoviesRepository : MoviesDataSource {

    private val listMovies = MutableLiveData<List<MoviesEntity>>()
    private val listTvShows = MutableLiveData<List<MoviesEntity>>()
    private val listCredits = MutableLiveData<List<CrewEntity>>()
    private val listMoviesDetail = MutableLiveData<MoviesEntity>()
    private val listTvShowsDetail = MutableLiveData<MoviesEntity>()
    private val listCountries = MutableLiveData<List<CountriesEntity>>()
    private val listLanguage = MutableLiveData<List<LanguageEntity>>()

    companion object {
        const val TAG = "MoviesRepository"
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000
        internal const val APP_ID = BuildConfig.TMDB_API_KEY

        @Volatile
        private var instance: FakeMoviesRepository? = null

        fun getInstance(): FakeMoviesRepository =
            instance
                ?: synchronized(this) {
                    instance
                        ?: FakeMoviesRepository()
                }
    }


    // load data movies from rest api
    override fun loadAllMovies(): LiveData<List<MoviesEntity>> {
        CoroutineScope(Dispatchers.IO).launch {
            delay(SERVICE_LATENCY_IN_MILLIS)

            val client = ApiConfig.getApiService().getAllMovies(APP_ID)
            client.enqueue(object : Callback<MoviesResponse> {
                override fun onResponse(
                    call: Call<MoviesResponse>,
                    response: Response<MoviesResponse>
                ) {
                    if (response.isSuccessful) {
                        val listMapMovies =
                            response.body()?.results?.let { MappingHelper.moviesMapFromEntityList(it) }
                        if (listMapMovies != null) {
                            listMovies.postValue(listMapMovies)
                        }

                    } else {
                        Log.e(TAG, "notSuccessfull Response: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                    Log.e(TAG, "onFailure: ${t.message.toString()}")
                }
            })
        }
        return listMovies
    }

    override fun loadAllTvShows(): LiveData<List<MoviesEntity>> {
        CoroutineScope(Dispatchers.IO).launch {
            delay(SERVICE_LATENCY_IN_MILLIS)

            val client = ApiConfig.getApiService().getAllTvShows(APP_ID)
            client.enqueue(object : Callback<MoviesResponse> {
                override fun onResponse(
                    call: Call<MoviesResponse>,
                    response: Response<MoviesResponse>
                ) {
                    if (response.isSuccessful) {
                        val listMapTvShows =
                            response.body()?.results?.let { MappingHelper.tvShowsMapFromEntityList(it) }
                        if (listMapTvShows != null) {
                            listTvShows.postValue(listMapTvShows)
                        }

                    } else {
                        Log.e(TAG, "notSuccessfulResponse: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                    Log.e(TAG, "onFailure: ${t.message.toString()}")
                }
            })
        }
        return listTvShows
    }

    // load detail movies from rest api
    override fun loadDetailMovies(moviesId: Int): LiveData<MoviesEntity> {
        CoroutineScope(Dispatchers.IO).launch {
            delay(SERVICE_LATENCY_IN_MILLIS)

            val client = ApiConfig.getApiService().getDetailMovies(moviesId, APP_ID)
            client.enqueue(object : Callback<MoviesObject> {
                override fun onResponse(
                    call: Call<MoviesObject>,
                    response: Response<MoviesObject>
                ) {
                    if (response.isSuccessful) {
                        val listMapMovies = response.body()?.let { MappingHelper.mapToEntityMovies(it) }
                        if (listMapMovies != null) {
                            listMoviesDetail.postValue(listMapMovies)
                        }

                    } else {
                        Log.e(TAG, "notSuccessfull Response: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<MoviesObject>, t: Throwable) {
                    Log.e(TAG, "onFailure: ${t.message.toString()}")
                }
            })

        }
        return listMoviesDetail
    }

    override fun loadCreditsMovies(moviesId: Int): LiveData<List<CrewEntity>> {
        CoroutineScope(Dispatchers.IO).launch {
            delay(SERVICE_LATENCY_IN_MILLIS)

            val client = ApiConfig.getApiService().getCreditsMovies(moviesId, APP_ID)
            client.enqueue(object : Callback<CreditsResponse> {
                override fun onResponse(
                    call: Call<CreditsResponse>,
                    response: Response<CreditsResponse>
                ) {
                    if (response.isSuccessful) {
                        val listMapCredits = response.body()?.crew
                        if (listMapCredits != null) {
                            val creditsList = ArrayList<CrewEntity>()
                            for (responseCredits in listMapCredits) {
                                if (responseCredits.job == "Director") {
                                    val crewItem = CrewEntity(
                                        responseCredits.id,
                                        responseCredits.name,
                                        responseCredits.job
                                    )
                                    creditsList.add(crewItem)
                                }
                            }
                            listCredits.postValue(creditsList)
                        }

                    } else {
                        Log.e(TAG, "notSuccessfull Response: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<CreditsResponse>, t: Throwable) {
                    Log.e(TAG, "onFailure: ${t.message.toString()}")
                }
            })

        }
        return listCredits
    }

    // load detail tv shows from rest api
    override fun loadDetailTvShows(moviesId: Int): LiveData<MoviesEntity> {
        CoroutineScope(Dispatchers.IO).launch {
            delay(SERVICE_LATENCY_IN_MILLIS)

            val client = ApiConfig.getApiService().getDetailTvShows(moviesId, APP_ID)
            client.enqueue(object : Callback<MoviesObject> {
                override fun onResponse(
                    call: Call<MoviesObject>,
                    response: Response<MoviesObject>
                ) {
                    if (response.isSuccessful) {
                        val listMapMovies = response.body()?.let { MappingHelper.mapToEntityTvShows(it) }
                        if (listMapMovies != null) {
                            listTvShowsDetail.postValue(listMapMovies)
                        }

                    } else {
                        Log.e(TAG, "notSuccessfull Response: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<MoviesObject>, t: Throwable) {
                    Log.e(TAG, "onFailure: ${t.message.toString()}")
                }
            })

        }
        return listTvShowsDetail
    }

    // load data countries from rest api
    override fun loadCountries(originCountry: List<String>): LiveData<List<CountriesEntity>> {
        CoroutineScope(Dispatchers.IO).launch {
            delay(SERVICE_LATENCY_IN_MILLIS)

            val client = ApiConfig.getApiService().getCountries(APP_ID)
            client.enqueue(object : Callback<List<CountriesResponse>> {
                override fun onResponse(
                    call: Call<List<CountriesResponse>>,
                    response: Response<List<CountriesResponse>>
                ) {
                    if (response.isSuccessful) {
                        val listMapCountries = response.body()
                        if (listMapCountries != null) {

                            val countriesList = ArrayList<CountriesEntity>()
                            for (countriesResponse in listMapCountries) {

                                if (originCountry.contains(countriesResponse.iso31661)) {
                                    val countriesEntity = CountriesEntity(
                                        countriesResponse.iso31661,
                                        countriesResponse.englishName
                                    )
                                    countriesList.add(countriesEntity)
                                }
                            }
                            listCountries.postValue(countriesList)
                        }

                    } else {
                        Log.e(TAG, "notSuccessfulResponse: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<List<CountriesResponse>>, t: Throwable) {
                    Log.e(TAG, "onFailure: ${t.message.toString()}")
                }
            })

        }
        return listCountries
    }

    // load data countries from rest api
    override fun loadLanguage(language: String): LiveData<List<LanguageEntity>> {
        CoroutineScope(Dispatchers.IO).launch {
            delay(SERVICE_LATENCY_IN_MILLIS)

            val client = ApiConfig.getApiService().getLanguages(APP_ID)
            client.enqueue(object : Callback<List<LanguageResponse>> {
                override fun onResponse(
                    call: Call<List<LanguageResponse>>,
                    response: Response<List<LanguageResponse>>
                ) {
                    if (response.isSuccessful) {
                        val listMapLanguage = response.body()
                        if (listMapLanguage != null) {

                            val languageList = ArrayList<LanguageEntity>()
                            for (languageResponse in listMapLanguage) {

                                if (languageResponse.iso6391 == language) {
                                    val languageEntity = LanguageEntity(
                                        languageResponse.iso6391,
                                        languageResponse.englishName,
                                        languageResponse.name
                                    )
                                    languageList.add(languageEntity)
                                }
                            }
                            listLanguage.postValue(languageList)
                        }

                    } else {
                        Log.e(TAG, "notSuccessfulResponse: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<List<LanguageResponse>>, t: Throwable) {
                    Log.e(TAG, "onFailure: ${t.message.toString()}")
                }
            })

        }
        return listLanguage
    }

}