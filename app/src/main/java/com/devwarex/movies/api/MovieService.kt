package com.devwarex.movies.api

import com.devwarex.movies.model.Genres
import com.devwarex.movies.model.Movie
import com.devwarex.movies.model.Movies
import com.devwarex.movies.util.EndPoint
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET(EndPoint.DISCOVER_MOVIES)
    suspend fun getMoviesByGenreId(
        @Query(EndPoint.API_KEY_QUERY)key: String,
        @Query(EndPoint.WITH_GENRES_QUERY) genreId: Int,
        @Query(EndPoint.PAGE_QUERY) int: Int
    ): Movies

    @GET(EndPoint.POPULAR_MOVIES)
    suspend fun getPopularMovies(
        @Query(EndPoint.API_KEY_QUERY)key: String,
        @Query(EndPoint.PAGE_QUERY) int: Int
    ): Movies

    @GET(EndPoint.SEARCH_MOVIES)
    suspend fun getQueryMovies(
        @Query(EndPoint.API_KEY_QUERY)key: String,
        @Query(EndPoint.SEARCH_QUERY) query: String,
        @Query(EndPoint.PAGE_QUERY) int: Int
    ): Movies

    @GET(EndPoint.GENRES_LIST)
    suspend fun getGenres(
        @Query(EndPoint.API_KEY_QUERY)key: String
    ): Genres

    @GET(EndPoint.MOVIE_BY_ID)
    suspend fun getMovieById(
        @Path(EndPoint.MOVIE_ID_PATH) movieId: Int,
        @Query(EndPoint.API_KEY_QUERY) key: String
    ): Movie
}