package com.devwarex.movies.adapter

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.devwarex.movies.api.MovieService
import com.devwarex.movies.model.Movie
import javax.inject.Inject

class MoviesPagingSource @Inject constructor(
    private val client: MovieService
) : PagingSource<Int,Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val nextPageNumber = params.key ?: 1
            val list  = client.getMovies(int = nextPageNumber)
            Log.e("SIZE","${list.results.size}")
            LoadResult.Page(
                data = list.results,
                prevKey = if (list.page > 1) list.page -1 else null,
                nextKey = list.page + 1
            )
        } catch (e: Throwable) {
            Log.e("movies","${e.message}")
            LoadResult.Error(e)
        }


    }
}