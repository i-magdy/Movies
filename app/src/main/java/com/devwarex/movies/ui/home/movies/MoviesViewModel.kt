package com.devwarex.movies.ui.home.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.devwarex.movies.data.MoviesPagingSource
import com.devwarex.movies.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val pager: MoviesPagingSource
): ViewModel() {


    val items: Flow<PagingData<Movie>> = Pager(
        config = PagingConfig(pageSize = 20, enablePlaceholders = false),
        pagingSourceFactory = { pager }
    ).flow.cachedIn(viewModelScope)

}