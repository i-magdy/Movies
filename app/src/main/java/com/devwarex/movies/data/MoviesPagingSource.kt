package com.devwarex.movies.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.devwarex.movies.api.MovieService
import com.devwarex.movies.di.NamedApiKey
import com.devwarex.movies.model.Movie
import com.devwarex.movies.repo.PagingSourceType
import javax.inject.Inject

class MoviesPagingSource @Inject constructor(
    private val service: MovieService,
    private val type: PagingSourceType,
    private val query: String,
    private val genreId: Int,
    private val apiKey: String
) : PagingSource<Int,Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val nextPageNumber = params.key ?: 1
        return getApiResult(nextPage = nextPageNumber)

    }

    private suspend fun getApiResult(nextPage: Int): LoadResult<Int, Movie>{
        return when(type){

            PagingSourceType.POPULAR ->{
                try {
                    val result  = service.getPopularMovies(int = nextPage, key = apiKey)
                    if (result.results.isNullOrEmpty()) return LoadResult.Error(Throwable("empty"))
                    LoadResult.Page(
                        data = result.results,
                        prevKey = if (result.page > 1) result.page -1 else null,
                        nextKey = result.page + 1
                    )
                }catch (e: Throwable) {
                    Log.e("movies","${e.message}")
                    LoadResult.Error(e)
                }
            }

            PagingSourceType.QUERY -> {
                try {
                    val result  = service.getQueryMovies(int = nextPage, query = query, key = apiKey)
                    if (result.results.isNullOrEmpty()) return LoadResult.Error(Throwable("empty"))
                    LoadResult.Page(
                        data = result.results,
                        prevKey = if (result.page > 1) result.page -1 else null,
                        nextKey = result.page + 1
                    )
                }catch (e: Throwable) {
                    Log.e("movies","${e.message}")
                    LoadResult.Error(e)
                }
            }

            PagingSourceType.GENRE ->{
                try {
                    val result  = service.getMoviesByGenreId(int = nextPage, genreId = genreId, key = apiKey)
                    if (result.results.isNullOrEmpty()) return LoadResult.Error(Throwable("empty"))
                    LoadResult.Page(
                        data = result.results,
                        prevKey = if (result.page > 1) result.page -1 else null,
                        nextKey = result.page + 1
                    )
                }catch (e: Throwable) {
                    Log.e("movies","${e.message}")
                    LoadResult.Error(e)
                }
            }

        }
    }
}