package com.devwarex.movies.repo

import com.devwarex.movies.api.MovieService
import com.devwarex.movies.data.MoviesPagingSource

object MovieSourceRepo {
    fun getQueryMoviesSource(query: String,service: MovieService) =
        MoviesPagingSource(query = query, type = PagingSourceType.QUERY, service = service, genreId = 0)
    fun getGenreMoviesSource(genreId: Int,service: MovieService) =
        MoviesPagingSource(query = "", type = PagingSourceType.GENRE, service = service, genreId = genreId)
    fun getPopularMovieSource(service: MovieService) =
        MoviesPagingSource(query = "", type = PagingSourceType.POPULAR, service = service, genreId = 0)
}

enum class PagingSourceType{ QUERY,GENRE,POPULAR}