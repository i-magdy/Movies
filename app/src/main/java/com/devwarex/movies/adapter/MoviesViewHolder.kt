package com.devwarex.movies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.devwarex.movies.R
import com.devwarex.movies.model.Movie
import com.devwarex.movies.util.EndPoint

class MoviesViewHolder(
    val item: View
): RecyclerView.ViewHolder(item) {

    private val glide = Glide.with(item.context)
    private val title = item.findViewById<TextView>(R.id.item_movie_title)
    private val image = item.findViewById<ImageView>(R.id.item_movie_image)
    private val release = item.findViewById<TextView>(R.id.item_movie_release)

    fun onBind(movie: Movie?,listener: MovieAdapterListener){
        if (movie != null) {
            title.text = movie.title
            val url = EndPoint.IMAGE_BASE_URL+movie.poster_path
            release.text = movie.release_date
            glide.load(url).placeholder(R.drawable.alt_movie).error(R.drawable.alt_movie).into(image)
            item.setOnClickListener { listener.onMovieClick(movie.id) }
        }else{
            title.text = null
            release.text = null
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