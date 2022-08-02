package com.devwarex.movies.util

object EndPoint {
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/original"
    const val GENRES_LIST = "genre/movie/list"
    const val POPULAR_MOVIES = "movie/popular"
    const val DISCOVER_MOVIES = "discover/movie"
    const val SEARCH_MOVIES = "search/movie"
    const val MOVIE_BY_ID = "movie/{movie_id}"
    const val WITH_GENRES_QUERY = "with_genres"
    const val PAGE_QUERY = "page"
    const val SEARCH_QUERY = "query"
    const val LANG_QUERY = "language"
    const val MOVIE_ID_PATH = "movie_id"

    //keys
    const val GENRE_ID_KEY: String = "genre id key"
}