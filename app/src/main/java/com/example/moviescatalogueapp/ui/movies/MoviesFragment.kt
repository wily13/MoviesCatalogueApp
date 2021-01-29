package com.example.moviescatalogueapp.ui.movies

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviescatalogueapp.databinding.FragmentMoviesBinding
import com.example.moviescatalogueapp.viewmodel.MoviesViewModel
import com.example.moviescatalogueapp.viewmodel.ViewModelFactory

class MoviesFragment : Fragment() {

    private lateinit var fragmentMoviesBinding: FragmentMoviesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentMoviesBinding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        return fragmentMoviesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val repository = ViewModelFactory.getInstance()
            val viewModel = ViewModelProvider(this, repository).get(MoviesViewModel::class.java)
            val moviesAdapter = MoviesAdapter()

            fragmentMoviesBinding.progressBar.visibility = View.VISIBLE
            viewModel.getAllMovies().observe(viewLifecycleOwner, Observer { movies ->
                Log.e("Cek", "MOvies: "+ movies)
                fragmentMoviesBinding.progressBar.visibility = View.GONE
                if (movies != null) {
                    moviesAdapter.setMovies(movies)
                    moviesAdapter.notifyDataSetChanged()
                }
            })

            with(fragmentMoviesBinding.rvMovies){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = moviesAdapter
            }
        }
    }
}