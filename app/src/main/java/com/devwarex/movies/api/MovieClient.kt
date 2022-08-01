package com.devwarex.movies.api

import com.devwarex.movies.api.EndPoint.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object MovieClient {

    private val logger = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }
    private val client = OkHttpClient.Builder()
        .addInterceptor(logger)
        .connectTimeout(30,TimeUnit.SECONDS)
        .build()

    fun create(): MovieService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        //.addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
        .create(MovieService::class.java)

}