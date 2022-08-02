package com.devwarex.movies.api

import com.devwarex.movies.model.Genres
import com.devwarex.movies.model.Movies
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET(EndPoint.DISCOVER_MOVIES)
    suspend fun getMovies(@Query("api_key")key: String = "26ec0a29e13214313168687de4abbaf5",@Query("page") int: Int): Movies

    @GET(EndPoint.GENRES_LIST)
    suspend fun getGenres(@Query("api_key")key: String = "26ec0a29e13214313168687de4abbaf5"): Genres
}