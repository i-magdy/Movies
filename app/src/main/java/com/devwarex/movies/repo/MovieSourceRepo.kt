package com.devwarex.movies.repo

import com.devwarex.movies.api.MovieService
import com.devwarex.movies.data.MoviesPagingSource

object MovieSourceRepo {
    fun getQueryMoviesSource(
        query: String,
        service: MovieService,
        apikey: String,
        lang: String
    ) = MoviesPagingSource(
            query = query,
            type = PagingSourceType.QUERY,
            service = service,
            genreId = 0,
            apiKey = apikey,
            lang = lang
        )

    fun getGenreMoviesSource(
        genreId: Int,
        service: MovieService,
        apikey: String,
        lang: String
    ) = MoviesPagingSource(
            query = "",
            type = PagingSourceType.GENRE,
            service = service,
            genreId = genreId,
            apiKey = apikey,
            lang = lang
        )

    fun getPopularMovieSource(
        service: MovieService,
        apikey: String,
        lang: String
    ) = MoviesPagingSource(
            query = "",
            type = PagingSourceType.POPULAR,
            service = service,
            genreId = 0,
            apiKey = apikey,
            lang = lang
        )
}
enum class PagingSourceType{ QUERY,GENRE,POPULAR}