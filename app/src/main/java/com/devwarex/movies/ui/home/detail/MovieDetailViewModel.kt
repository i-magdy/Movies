package com.devwarex.movies.ui.home.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devwarex.movies.data.ApiResource
import com.devwarex.movies.model.Credits
import com.devwarex.movies.model.Images
import com.devwarex.movies.model.MovieDetail
import com.devwarex.movies.repo.MovieDetailRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val repo: MovieDetailRepo
): ViewModel(){

    private val _movie = MutableLiveData<ApiResource<MovieDetail>>()
    private val _images = MutableLiveData<Images>()
    private val _credits = MutableLiveData<ApiResource<Credits>>()
    val movie: LiveData<ApiResource<MovieDetail>> get() = _movie
    val images: LiveData<Images> get() = _images
    val credits: LiveData<ApiResource<Credits>> get() = _credits
    init {
       viewModelScope.launch {
           launch { repo.movie.collect{ _movie.value = it } }
           launch { repo.images.collect{ _images.value = it } }
           launch { repo.credits.collect{ _credits.value = it } }
       }
    }

    fun getMovie(movieId: Int){
        repo.getMovieById(movieId)
    }

    override fun onCleared() {
        super.onCleared()
        repo.cancelJob()
    }
}