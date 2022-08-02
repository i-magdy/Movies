package com.devwarex.movies.ui.home.movies.adapter

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.devwarex.movies.util.EndPoint.GENRE_ID_KEY
import com.devwarex.movies.model.Genre
import com.devwarex.movies.ui.home.movies.MoviesFragment

class MoviesPagerAdapter(
    fragment: Fragment
): FragmentStateAdapter(fragment) {
    private var genres: List<Genre> = listOf()
    override fun getItemCount(): Int = genres.size

    override fun createFragment(position: Int): Fragment {
        val fa = MoviesFragment()
        fa.arguments = Bundle().apply {
            putInt(GENRE_ID_KEY,genres[position].id)
        }
        return fa
    }


    @SuppressLint("NotifyDataSetChanged")
    fun setGenres(s: List<Genre>){
        this.genres = s
        notifyDataSetChanged()
    }
}