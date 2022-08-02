package com.devwarex.movies.repo

import com.devwarex.movies.api.MovieService
import com.devwarex.movies.di.NamedApiKey
import com.devwarex.movies.model.Images
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

class MovieImagesRepo @Inject constructor(
    private val service: MovieService,
    @NamedApiKey private val apiKey: String
) {

    private val job = CoroutineScope(Dispatchers.Default)
    val images = Channel<Images>(Channel.UNLIMITED)

    fun getImages(movieId: Int){
        job.launch {
            try {
                images.send(service.getMovieImagesById(movieId = movieId, key =apiKey))
            }catch (e: HttpException){

            }
        }
    }

    fun cancelJob(){
        job.cancel()
    }
}