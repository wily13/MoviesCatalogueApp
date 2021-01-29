package com.example.moviescatalogueapp.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviescatalogueapp.data.source.response.GenresItem
import com.example.moviescatalogueapp.databinding.ItemsGenresBinding
import kotlin.collections.ArrayList

class GenresAdapter : RecyclerView.Adapter<GenresAdapter.GenresViewHolder>() {

    private var listGenres = ArrayList<GenresItem>()

    fun setGenres(genre: List<GenresItem>) {
        if (genre.isNullOrEmpty()) return
        this.listGenres.clear()
        this.listGenres.addAll(genre)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenresViewHolder {
        val itemsGenresBinding =
            ItemsGenresBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GenresViewHolder(itemsGenresBinding)
    }

    override fun onBindViewHolder(holder: GenresViewHolder, position: Int) {
        val movies = listGenres[position]
        holder.bind(movies)
    }

    override fun getItemCount(): Int = listGenres.size

    inner class GenresViewHolder(private val binding: ItemsGenresBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(genre: GenresItem) {
            with(binding) {
                tvItemTitle.text = genre.name

            }
        }
    }


}