package com.app.movienetflix.movie.recycleview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.app.movienetflix.R
import com.app.movienetflix.movie.Movie
import com.squareup.picasso.Picasso

class MovieRecycleView(private val movieList: List<Movie>) :
    RecyclerView.Adapter<MovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_list_recycle_view_layout, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movieImage = movieList[position].imageMovie
        val movieSynopsis = movieList[position].synopsisMovie
        val movieDuration = movieList[position].durationMovie
        Picasso.get().load(movieImage).into(holder.movieImage)
        holder.movieImage.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_global_detailsMovieFragment, bundleOf(
                    "imageURL" to movieImage,
                    "synopsis" to movieSynopsis,
                    "duration" to movieDuration
                ))
        }
    }
}

class MovieViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    val movieImage = v.findViewById<ImageView>(R.id.movieImage)
}