package com.devwarex.movies.repo

import com.devwarex.movies.api.MovieService
import com.devwarex.movies.data.ApiResource
import com.devwarex.movies.di.NamedApiKey
import com.devwarex.movies.di.NamedLang
import com.devwarex.movies.model.Genres
import com.devwarex.movies.util.ApiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

class GenresRepo @Inject constructor(
    private val service: MovieService,
    @NamedApiKey val apiKey: String,
    @NamedLang private val lang: String
) {

    val genres = MutableStateFlow<ApiResource<Genres>>(ApiResource.Loading(ApiState.LOADING,null))
    private val job = CoroutineScope(Dispatchers.Default)


    fun getGenres(){
        job.launch {
            try {
                genres.value = ApiResource.Success(
                    state = ApiState.SUCCESS,
                    data = service.getGenres(key = apiKey, lang = lang)
                )
            }catch (e: HttpException){
                genres.value = ApiResource.Error(
                    state = ApiState.ERROR,
                    message = e.message
                )
            }
        }
    }

    fun cancelJob(){
        job.cancel()
    }
}