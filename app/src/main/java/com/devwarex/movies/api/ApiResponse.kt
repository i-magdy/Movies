package com.devwarex.movies.api

sealed class ApiResponse<R>{

    class ApiEmptyResponse<T> : ApiResponse<T>()


}
