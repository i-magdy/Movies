package com.devwarex.movies.ui.home.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devwarex.movies.data.ApiResource
import com.devwarex.movies.model.Movie
import com.devwarex.movies.repo.MovieDetailRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val repo: MovieDetailRepo
): ViewModel(){

    private val _movie = MutableLiveData<ApiResource<Movie>>()
    val movie: LiveData<ApiResource<Movie>> get() = _movie

    init {
       viewModelScope.launch {
           repo.movie.collect{ _movie.value = it }
       }
    }

    fun getMovie(movieId: Int){
        repo.getMovieById(movieId)
    }
}