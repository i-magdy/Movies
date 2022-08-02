package com.devwarex.movies.data

import com.devwarex.movies.util.ApiState

sealed class ApiResource<T>{
    data class Loading<T>(val state: ApiState,val data: T?):ApiResource<T>()
    data class Success<T>(val state: ApiState,val data: T): ApiResource<T>()
    data class Error<T>(val state: ApiState,val data:T? = null,val message: String?): ApiResource<T>()
}
