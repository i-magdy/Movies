package com.devwarex.movies.api

import com.devwarex.movies.model.*
import com.devwarex.movies.util.EndPoint
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET(EndPoint.DISCOVER_MOVIES)
    suspend fun getMoviesByGenreId(
        @Query(EndPoint.API_KEY_QUERY)key: String,
        @Query(EndPoint.WITH_GENRES_QUERY) genreId: Int,
        @Query(EndPoint.PAGE_QUERY) int: Int,
        @Query(EndPoint.LANG_QUERY) lang: String
    ): Movies

    @GET(EndPoint.POPULAR_MOVIES)
    suspend fun getPopularMovies(
        @Query(EndPoint.API_KEY_QUERY)key: String,
        @Query(EndPoint.PAGE_QUERY) int: Int,
        @Query(EndPoint.LANG_QUERY) lang: String
    ): Movies

    @GET(EndPoint.SEARCH_MOVIES)
    suspend fun getQueryMovies(
        @Query(EndPoint.API_KEY_QUERY)key: String,
        @Query(EndPoint.SEARCH_QUERY) query: String,
        @Query(EndPoint.PAGE_QUERY) int: Int,
        @Query(EndPoint.LANG_QUERY) lang: String
    ): Movies

    @GET(EndPoint.GENRES_LIST)
    suspend fun getGenres(
        @Query(EndPoint.API_KEY_QUERY)key: String,
        @Query(EndPoint.LANG_QUERY) lang: String
    ): Genres

    @GET(EndPoint.MOVIE_BY_ID)
    suspend fun getMovieById(
        @Path(EndPoint.MOVIE_ID_PATH) movieId: Int,
        @Query(EndPoint.API_KEY_QUERY) key: String,
        @Query(EndPoint.LANG_QUERY) lang: String
    ): MovieDetail

    @GET(EndPoint.MOVIE_IMAGES)
    suspend fun getMovieImagesById(
        @Path(EndPoint.MOVIE_ID_PATH) movieId: Int,
        @Query(EndPoint.API_KEY_QUERY) key: String
    ): Images

    @GET(EndPoint.MOVIE_CREDITS)
    suspend fun getMovieCredits(
        @Path(EndPoint.MOVIE_ID_PATH) movieId: Int,
        @Query(EndPoint.API_KEY_QUERY) key: String
    ): Credits
}