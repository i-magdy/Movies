package com.devwarex.movies.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.devwarex.movies.api.MovieService
import com.devwarex.movies.di.NamedApiKey
import com.devwarex.movies.model.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesRepo @Inject constructor(
    private val service: MovieService,
    @NamedApiKey private val apiKey: String
) {

    fun getMoviesResult(genreId: Int): Flow<PagingData<Movie>> {
       return if (genreId == -1){
           Pager(
               config = PagingConfig(pageSize = 20, enablePlaceholders = false),
               pagingSourceFactory = { MovieSourceRepo.getPopularMovieSource(service = service, apikey = apiKey) }
           ).flow
       } else{
           Pager(
               config = PagingConfig(pageSize = 20, enablePlaceholders = false),
               pagingSourceFactory = { MovieSourceRepo.getGenreMoviesSource(genreId = genreId,service = service, apikey = apiKey) }
           ).flow
       }
    }
}