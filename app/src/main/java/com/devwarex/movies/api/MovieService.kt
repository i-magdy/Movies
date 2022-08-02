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
        @Query("api_key")key: String = "26ec0a29e13214313168687de4abbaf5",
        @Query(EndPoint.WITH_GENRES_QUERY) genreId: Int,
        @Query("page") int: Int
    ): Movies

    @GET(EndPoint.POPULAR_MOVIES)
    suspend fun getPopularMovies(
        @Query("api_key")key: String = "26ec0a29e13214313168687de4abbaf5",
        @Query("page") int: Int
    ): Movies

    @GET(EndPoint.SEARCH_MOVIES)
    suspend fun getQueryMovies(
        @Query("api_key")key: String = "26ec0a29e13214313168687de4abbaf5",
        @Query(EndPoint.SEARCH_QUERY) query: String,
        @Query("page") int: Int
    ): Movies

    @GET(EndPoint.GENRES_LIST)
    suspend fun getGenres(
        @Query("api_key")key: String = "26ec0a29e13214313168687de4abbaf5"
    ): Genres

    @GET(EndPoint.MOVIE_BY_ID)
    suspend fun getMovieById(
        @Path(EndPoint.MOVIE_ID_PATH) movieId: Int,
        @Query("api_key")key: String = "26ec0a29e13214313168687de4abbaf5"
    ): Movie
}