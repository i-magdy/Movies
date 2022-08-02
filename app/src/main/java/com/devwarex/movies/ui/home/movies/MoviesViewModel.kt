package com.devwarex.movies.ui.home.movies

import androidx.lifecycle.ViewModel
import androidx.paging.*
import com.devwarex.movies.model.Movie
import com.devwarex.movies.repo.MoviesRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repo: MoviesRepo
): ViewModel() {


    fun getMoviesByGenre(genreId: Int): Flow<PagingData<Movie>> =  repo.getMoviesResult(genreId = genreId)

}