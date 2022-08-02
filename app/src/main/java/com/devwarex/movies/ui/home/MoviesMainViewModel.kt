package com.devwarex.movies.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesMainViewModel @Inject constructor(): ViewModel() {

    private val _movieId = MutableLiveData<Int>()
    private val _searchQuery = MutableLiveData<String>()
    private val _title = MutableLiveData<String>()
    val movieId: LiveData<Int> get() = _movieId
    val searchQuery: LiveData<String> get() = _searchQuery
    val title: LiveData<String> get() = _title

    fun setQuery(text: String){
        _searchQuery.value = text
    }

    fun setMovieId(id: Int){
        _movieId.value = id
    }

    fun setMovieTitle(text: String){
        _title.value = text
    }
}