package com.app.movienetflix.movie.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.app.movienetflix.R
import com.app.movienetflix.movie.Movie
import com.app.movienetflix.movie.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_create_movie.*

class CreateMovieFragment : Fragment(), View.OnClickListener {
    val menuViewModel by activityViewModels<MovieViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        submitNewMovie.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            submitNewMovie -> {
                val movie = Movie(
                    titleMovie = titleMovie.text.toString(),
                    durationMovie = durationMovie.text.toString(),
                    imageMovie = imageMovie.text.toString(),
                    synopsisMovie = synopsisMovie.text.toString()
                )
                if (titleMovie.text.toString() == "" ||
                    durationMovie.text.toString() == "" ||
                    imageMovie.text.toString() == "" ||
                    synopsisMovie.text.toString() == ""
                ) {
                    Toast.makeText(this.context, "Must be Field", Toast.LENGTH_SHORT).show()
                } else {
                    menuViewModel.saveMovie(movie)
                    Toast.makeText(this.context, "Added New Movie", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}