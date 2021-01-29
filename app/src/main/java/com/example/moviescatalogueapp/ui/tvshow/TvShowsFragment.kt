package com.example.moviescatalogueapp.ui.tvshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviescatalogueapp.databinding.FragmentTvShowsBinding
import com.example.moviescatalogueapp.viewmodel.TvShowViewModel
import com.example.moviescatalogueapp.viewmodel.ViewModelFactory

class TvShowsFragment : Fragment() {

    private lateinit var fragmentTvShowsBinding: FragmentTvShowsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentTvShowsBinding = FragmentTvShowsBinding.inflate(layoutInflater, container, false)
        return fragmentTvShowsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null){
            val factory = ViewModelFactory.getInstance()
            val viewModel = ViewModelProvider(this, factory).get(TvShowViewModel::class.java)
            val tvShowsAdapter = TvShowsAdapter()

            fragmentTvShowsBinding.progressBar.visibility = View.VISIBLE
            viewModel.getAllTvShows().observe(viewLifecycleOwner, Observer { tvShows ->
                fragmentTvShowsBinding.progressBar.visibility = View.GONE
                if (tvShows != null) {
                    tvShowsAdapter.setMovies(tvShows)
                    tvShowsAdapter.notifyDataSetChanged()
                }
            })

            with(fragmentTvShowsBinding.rvTvShow){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowsAdapter
            }
        }
    }
}