package com.devwarex.movies.repo

import com.devwarex.movies.api.MovieService
import com.devwarex.movies.data.ApiResource
import com.devwarex.movies.di.NamedApiKey
import com.devwarex.movies.di.NamedLang
import com.devwarex.movies.model.Movie
import com.devwarex.movies.util.ApiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

class MovieDetailRepo @Inject constructor(
    private val service: MovieService,
    @NamedApiKey private val apiKey: String,
    @NamedLang private val lang: String
) {

    val movie = MutableStateFlow<ApiResource<Movie>>(ApiResource.Loading(ApiState.LOADING,null))
    private val job = CoroutineScope(Dispatchers.Default)

    fun getMovieById(movieId: Int){
        job.launch {
            try {
               movie.value = ApiResource.Success(
                   data = service.getMovieById(movieId = movieId, key = apiKey, lang = lang),
                   state = ApiState.SUCCESS
               )
            }catch (e: HttpException){
                movie.value = ApiResource.Error(
                    message = e.message,
                    state = ApiState.ERROR
                )
            }
        }
    }

    fun cancelJob(){
        job.cancel()
    }
}