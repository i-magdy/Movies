package com.devwarex.movies.di

import com.devwarex.movies.api.MovieClient
import com.devwarex.movies.api.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MovieClientModule {

    @Singleton
    @Provides
    fun provideMovieClientService(): MovieService = MovieClient.create()
}