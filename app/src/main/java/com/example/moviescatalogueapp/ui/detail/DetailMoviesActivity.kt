package com.example.moviescatalogueapp.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.moviescatalogueapp.R
import com.example.moviescatalogueapp.data.model.MoviesEntity
import com.example.moviescatalogueapp.databinding.ActivityDetailMoviesBinding
import com.example.moviescatalogueapp.databinding.ContentDetailMoviesBinding
import com.example.moviescatalogueapp.utils.FormatedMethod
import com.example.moviescatalogueapp.viewmodel.DetailMoviesViewModel
import com.example.moviescatalogueapp.viewmodel.ViewModelFactory


class DetailMoviesActivity : AppCompatActivity() {

    companion object {
        const val TAG = "DetailMoviesActivity"
        const val EXTRA_MOVIES = "extra_movies"
        const val EXTRA_STATUS = "extra_status"
        const val EXTRA_TITLE_TAB = "movies"
    }

    private lateinit var contentDetailMoviesBinding: ContentDetailMoviesBinding
    private lateinit var activityDetailMoviesBinding: ActivityDetailMoviesBinding
    private lateinit var viewModel: DetailMoviesViewModel
    private lateinit var genresAdapter: GenresAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDetailMoviesBinding = ActivityDetailMoviesBinding.inflate(layoutInflater)
        contentDetailMoviesBinding = activityDetailMoviesBinding.moviesContent
        setContentView(activityDetailMoviesBinding.root)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance()
        viewModel = ViewModelProvider(this, factory).get(
            DetailMoviesViewModel::class.java
        )

        //List adapter genre
        genresAdapter = GenresAdapter()

        val extras = intent.extras
        if (extras != null) {
            val movieId = extras.getInt(EXTRA_MOVIES)
            val movieStatus = extras.getString(EXTRA_STATUS)

            Log.d(TAG, "Movies id is: $movieId")

            viewModel.setSelectedMovie(movieId)

            activityDetailMoviesBinding.progressBar.visibility = View.VISIBLE
            contentDetailMoviesBinding.clContentMovies.visibility = View.GONE
            activityDetailMoviesBinding.fab.visibility = View.GONE

            if (movieStatus == EXTRA_TITLE_TAB) {
                supportActionBar?.title = getString(R.string.title_tab_movies)
                // load detai movies
                viewModel.getMovieDetail().observe(this@DetailMoviesActivity, Observer { movies ->
                    activityDetailMoviesBinding.progressBar.visibility = View.GONE
                    contentDetailMoviesBinding.clContentMovies.visibility = View.VISIBLE
                    activityDetailMoviesBinding.fab.visibility = View.VISIBLE

                    movieStatus.let { populateMoviesDetail(movies) }
                })

            } else {
                supportActionBar?.title = getString(R.string.title_tab_tvshows)
                // load detai tv shows
                viewModel.getTvShowsDetail()
                    .observe(this@DetailMoviesActivity, Observer { tvShows ->
                        activityDetailMoviesBinding.progressBar.visibility = View.GONE
                        contentDetailMoviesBinding.clContentMovies.visibility = View.VISIBLE
                        activityDetailMoviesBinding.fab.visibility = View.VISIBLE

                        movieStatus.let { populateTvShowsDetail(tvShows) }
                    })
            }

        }

    }

    private fun shareApp(title: String) {
        val mimeType = "text/plain"
        ShareCompat.IntentBuilder
            .from(this@DetailMoviesActivity)
            .setType(mimeType)
            .setChooserTitle("Bagikan Film: \"${title}\", sekarang.")
            .startChooser()
    }

    private fun populateMoviesDetail(moviesEntity: MoviesEntity) {
        moviesEntity.apply {
            with(contentDetailMoviesBinding) {
                val yearRelease = if (releaseDate.isEmpty()) "-" else FormatedMethod.getYearRelease(releaseDate)
                tvTitle.text = StringBuilder("$title ($yearRelease)")
                tvDescription.text = overview
                tvStatus.text = status
                tvDaterelease.text = if (releaseDate.isEmpty()) "-" else FormatedMethod.getDateFormat(releaseDate)
                tvPopularity.text = FormatedMethod.roundOffDecimal(popularity)
                tvVoteAverage.text = FormatedMethod.roundOffDecimal(voteAverage)
                tvVoteCount.text = voteCount.toString()
                tvBudget.text = FormatedMethod.roundOffInt(budget)
                tvRevenue.text = FormatedMethod.roundOffInt(revenue)

                if (tagline.isNotEmpty()){
                    tvTagline.text = tagline
                }else{
                    tvTagline.isVisible = false
                }

                // set director
                viewModel.getCreditsMovie().observe(this@DetailMoviesActivity, Observer { credits ->
                    tvDirector.text = if (credits.isNotEmpty()) credits[0].name else "-"
                })

                // set language
                if (originalLanguage.isNotEmpty()) {
                    viewModel.getLanguage(originalLanguage)
                        .observe(this@DetailMoviesActivity, Observer { language ->
                            tvLanguage.text = language[0].englishName
                        })
                }else{
                    tvLanguage.text = "-"
                }

                // set genre movies
                if (genres.isNotEmpty()) {
                    genresAdapter.setGenres(genres)
                    genresAdapter.notifyDataSetChanged()
                }

                with(contentDetailMoviesBinding.rvGenre) {
                    layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    setHasFixedSize(true)
                    adapter = genresAdapter
                }

                // set image poster
                val imagePath = StringBuilder("${baseUrl}${posterPath}").toString()
                Glide.with(this@DetailMoviesActivity)
                    .load(imagePath)
                    .transform(RoundedCorners(20))
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(imgPoster)

                // set visibility textview
                tvFirstairDate.isVisible = false
                tvFirstairdateTitle.isVisible = false
                tvLastairDate.isVisible = false
                tvLastairdateTitle.isVisible = false
                tvCreator.isVisible = false
                tvCreatorTitle.isVisible = false
                tvType.isVisible = false
                tvTypeTitle.isVisible = false
                tvCountry.isVisible = false
                tvCountryTitle.isVisible = false

                // fab button share
                activityDetailMoviesBinding.fab.setOnClickListener {
                    shareApp(title)
                }
            }
        }
    }


    private fun populateTvShowsDetail(moviesEntity: MoviesEntity) {
        moviesEntity.apply {
            with(contentDetailMoviesBinding) {
                val yearRelease = if (lastAirDate.isEmpty()) "-" else FormatedMethod.getYearRelease(lastAirDate)
                tvTitle.text = StringBuilder("$name ($yearRelease)")
                tvFirstairDate.text = if (firstAirDate.isEmpty()) "-" else FormatedMethod.getDateFormat(firstAirDate)
                tvLastairDate.text = if (lastAirDate.isEmpty()) "-" else FormatedMethod.getDateFormat(lastAirDate)
                tvDescription.text = overview
                tvCreator.text = if (createdBy.isEmpty())  "-" else createdBy[0].name
                tvType.text = type
                tvStatus.text = status
                tvPopularity.text = FormatedMethod.roundOffDecimal(popularity)
                tvVoteAverage.text = FormatedMethod.roundOffDecimal(voteAverage)
                tvVoteCount.text = voteCount.toString()

                if (tagline.isNotEmpty()){
                    tvTagline.text = tagline
                }else{
                    tvTagline.isVisible = false
                }

                if (originCountry.isNotEmpty()) {
                    // set country
                    viewModel.getCountries(originCountry)
                        .observe(this@DetailMoviesActivity, Observer { countries ->
                            tvCountry.text = if (countries.isEmpty()) "-" else countries[0].name
                        })
                }else{
                    tvCountry.text = "-"
                }

                // set language
                if (originalLanguage.isNotEmpty()) {
                    viewModel.getLanguage(originalLanguage)
                        .observe(this@DetailMoviesActivity, Observer { language ->
                            tvLanguage.text = language[0].englishName
                        })
                }else{
                    tvLanguage.text = "-"
                }

                // set genre movies
                if (genres.isNotEmpty()) {
                    genresAdapter.setGenres(genres)
                    genresAdapter.notifyDataSetChanged()
                }

                with(contentDetailMoviesBinding.rvGenre) {
                    layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    setHasFixedSize(true)
                    adapter = genresAdapter
                }

                // set image poster
                val imagePath = StringBuilder("${baseUrl}${posterPath}").toString()
                Glide.with(this@DetailMoviesActivity)
                    .load(imagePath)
                    .transform(RoundedCorners(20))
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(imgPoster)

                // set visibility textview
                tvDaterelease.isVisible = false
                tvDatereleaseTitle.isVisible = false
                tvDirector.isVisible = false
                tvDirectorTitle.isVisible = false
                tvBudget.isVisible = false
                tvBudgetTitle.isVisible = false
                tvRevenue.isVisible = false
                tvRevenueTitle.isVisible = false

                // fab button share
                activityDetailMoviesBinding.fab.setOnClickListener {
                    shareApp(name)
                }
            }
        }
    }

}

