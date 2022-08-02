package com.devwarex.movies.repo

import com.devwarex.movies.api.MovieService
import com.devwarex.movies.data.MoviesPagingSource

object MovieSourceRepo {
    fun getQueryMoviesSource(
        query: String,
        service: MovieService,
        apikey: String
    ) = MoviesPagingSource(
            query = query,
            type = PagingSourceType.QUERY,
            service = service,
            genreId = 0,
            apiKey = apikey
        )

    fun getGenreMoviesSource(
        genreId: Int,
        service: MovieService,
        apikey: String
    ) = MoviesPagingSource(
            query = "",
            type = PagingSourceType.GENRE,
            service = service,
            genreId = genreId,
            apiKey = apikey
        )

    fun getPopularMovieSource(
        service: MovieService,
        apikey: String
    ) = MoviesPagingSource(
            query = "",
            type = PagingSourceType.POPULAR,
            service = service,
            genreId = 0,
            apiKey = apikey
        )
}
enum class PagingSourceType{ QUERY,GENRE,POPULAR}