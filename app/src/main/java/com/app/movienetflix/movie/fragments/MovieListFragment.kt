package com.app.movienetflix.movie.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.app.movienetflix.R
import com.app.movienetflix.movie.recycleview.MovieRecycleView
import com.app.movienetflix.movie.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movie_list.*

class MovieListFragment : Fragment() {
    val movieViewModel by activityViewModels<MovieViewModel>()
    lateinit var movieRecycleView: MovieRecycleView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gridRecycleView = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        movieListRecycleView.layoutManager = gridRecycleView

        movieViewModel.allMovie.observe(viewLifecycleOwner, Observer {
            movieRecycleView = MovieRecycleView(it)
            movieListRecycleView.adapter = movieRecycleView
        })
        movieViewModel.getAllMovie()
    }
}