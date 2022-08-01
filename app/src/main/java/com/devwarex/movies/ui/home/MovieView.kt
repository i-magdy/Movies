package com.devwarex.movies.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.devwarex.movies.model.Movie
import com.devwarex.movies.data.MoviesPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieView @Inject constructor(
    private val pager: MoviesPagingSource
): ViewModel() {

    val items: Flow<PagingData<Movie>> = Pager(
        config = PagingConfig(pageSize = 20, enablePlaceholders = false),
        pagingSourceFactory = { pager }
    ).flow.cachedIn(viewModelScope)

    fun sync(){
        viewModelScope.launch {
           items.collectLatest {
               it.map {m -> Log.e("movies","${m.title}")  }

           }
        }
    }


}