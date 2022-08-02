package com.devwarex.movies.ui.home.search

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.devwarex.movies.model.Movie
import com.devwarex.movies.repo.SearchMoviesRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SearchMoviesViewModel @Inject constructor(
    private val repo: SearchMoviesRepo
):ViewModel(){

    fun getMovies(text: String): Flow<PagingData<Movie>> = repo.getSearchMoviesResult(query = text)
}