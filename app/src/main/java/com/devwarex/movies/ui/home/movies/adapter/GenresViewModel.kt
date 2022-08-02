package com.devwarex.movies.ui.home.movies.adapter

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devwarex.movies.data.ApiResource
import com.devwarex.movies.model.Genres
import com.devwarex.movies.repo.GenresRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenresViewModel @Inject constructor(
    private val repo: GenresRepo
): ViewModel(){


    private val _genres = MutableLiveData<ApiResource<Genres>>()
    val genres: LiveData<ApiResource<Genres>> get() = _genres
    init {
        viewModelScope.launch {
            repo.genres.collect{ _genres.value = it }
        }
    }
    fun getGenres() = repo.getGenres()
}