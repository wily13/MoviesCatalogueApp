package com.example.moviescatalogueapp.ui.movies

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviescatalogueapp.R
import com.example.moviescatalogueapp.data.model.MoviesEntity
import com.example.moviescatalogueapp.databinding.ItemsMoviesBinding
import com.example.moviescatalogueapp.ui.detail.DetailMoviesActivity
import com.example.moviescatalogueapp.utils.FormatedMethod
import java.lang.StringBuilder
import kotlin.collections.ArrayList

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    private var listMovies = ArrayList<MoviesEntity>()

    fun setMovies(movies: List<MoviesEntity>?) {
        if (movies.isNullOrEmpty()) return
        this.listMovies.clear()
        this.listMovies.addAll(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val itemsMoviesBinding =
            ItemsMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(itemsMoviesBinding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movies = listMovies[position]
        holder.bind(movies)
    }

    override fun getItemCount(): Int = listMovies.size

    inner class MoviesViewHolder(private val binding: ItemsMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(moviesEntity: MoviesEntity) {
            with(binding) {
                moviesEntity.apply {
                    tvItemTitle.text = title
                    tvItemDate.text = if (releaseDate.isEmpty()) "-" else FormatedMethod.getDateFormat(releaseDate)
                    tvItemPopularity.text = FormatedMethod.getPopular(voteAverage)
                    itemView.setOnClickListener {
                        val intent = Intent(itemView.context, DetailMoviesActivity::class.java)
                        intent.putExtra(DetailMoviesActivity.EXTRA_MOVIES, moviesId)
                        intent.putExtra(DetailMoviesActivity.EXTRA_STATUS, "movies")
                        itemView.context.startActivity(intent)
                    }

                    val imagePath = StringBuilder("${baseUrl}${posterPath}").toString()
                    Glide.with(itemView.context)
                        .load(imagePath)
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                                .error(R.drawable.ic_error)
                        )
                        .into(imgPoster)
                }
            }
        }
    }


}