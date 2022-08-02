package com.devwarex.movies.di

import android.content.Context
import com.devwarex.movies.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    @NamedApiKey
    fun getMovieDbApiKey(@ApplicationContext context: Context): String = context.getString(R.string.api_key)

    @Singleton
    @Provides
    @NamedLang
    fun getMovieDbApiLang(@ApplicationContext context: Context): String =
        if (context.resources.configuration.locales[0].language == "ar") "ar" else "en-US"

}