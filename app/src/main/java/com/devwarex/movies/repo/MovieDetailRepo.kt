package com.devwarex.movies.repo

import com.devwarex.movies.api.MovieService
import com.devwarex.movies.data.ApiResource
import com.devwarex.movies.di.NamedApiKey
import com.devwarex.movies.di.NamedLang
import com.devwarex.movies.model.Credits
import com.devwarex.movies.model.Images
import com.devwarex.movies.model.MovieDetail
import com.devwarex.movies.util.ApiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

class MovieDetailRepo @Inject constructor(
    private val service: MovieService,
    private val imagesRepo: MovieImagesRepo,
    private val creditsRepo: CreditsRepo,
    @NamedApiKey private val apiKey: String,
    @NamedLang private val lang: String
) {

    val movie = MutableStateFlow<ApiResource<MovieDetail>>(ApiResource.Loading(ApiState.LOADING,null))
    val images: Flow<Images> = imagesRepo.images.receiveAsFlow()
    val credits: MutableStateFlow<ApiResource<Credits>> get() = creditsRepo.credits
    private val job = CoroutineScope(Dispatchers.Default)

    fun getMovieById(movieId: Int){
        imagesRepo.getImages(movieId)
        creditsRepo.getCredits(movieId)
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
        imagesRepo.cancelJob()
        creditsRepo.cancelJob()
    }
}