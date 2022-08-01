package com.devwarex.movies.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.devwarex.movies.model.Movie

class MoviesAdapter : PagingDataAdapter<Movie,MoviesViewHolder>(CALLBACK) {

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder = MoviesViewHolder.create(parent = parent)


    companion object{
        private val CALLBACK = object :DiffUtil.ItemCallback<Movie>(){
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem == newItem
        }
    }
}