package com.devwarex.movies.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.devwarex.movies.api.MovieService
import com.devwarex.movies.di.NamedApiKey
import com.devwarex.movies.di.NamedLang
import com.devwarex.movies.model.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchMoviesRepo @Inject constructor(
    private val service: MovieService,
    @NamedApiKey private val apikey: String,
    @NamedLang private val lang: String
) {


    fun getSearchMoviesResult(query: String): Flow<PagingData<Movie>>{
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { MovieSourceRepo.getQueryMoviesSource(query = query,service = service, apikey = apikey, lang = lang) }
        ).flow
    }
}