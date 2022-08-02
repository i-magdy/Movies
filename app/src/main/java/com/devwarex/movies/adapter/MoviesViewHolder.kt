package com.devwarex.movies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.devwarex.movies.R
import com.devwarex.movies.model.Movie

class MoviesViewHolder(
    item: View
): RecyclerView.ViewHolder(item) {

    private val glide = Glide.with(item.context)
    private val title = item.findViewById<TextView>(R.id.title)

    fun onBind(movie: Movie?,listener: MovieAdapterListener){
        if (movie != null) {
            title.text = movie.title
            title.setOnClickListener { listener.onMovieClick(movie.id) }
        }
    }

    companion object{
        fun create(parent: ViewGroup): MoviesViewHolder =
            MoviesViewHolder(
                item = LayoutInflater.from(parent.context)
                    .inflate(R.layout.movie_item,parent,false)
            )
    }
}