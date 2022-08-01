package com.devwarex.movies.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devwarex.movies.R
import com.devwarex.movies.adapter.MoviesAdapter
import com.google.android.material.progressindicator.LinearProgressIndicator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MoviesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)
        val viewModel: MovieView by viewModels()
        //viewModel.sync()
        val re = findViewById<RecyclerView>(R.id.re)
        val loading = findViewById<LinearProgressIndicator>(R.id.append_progress)
        val adapter = MoviesAdapter()
        re.adapter = adapter
        re.layoutManager = LinearLayoutManager(this)
        lifecycleScope.launchWhenCreated {
            viewModel.items.collectLatest {
               adapter.submitData(it)
            }
        }

        lifecycleScope.launchWhenCreated {
            adapter.loadStateFlow.collect{
                Log.e("loading","${it.source.prepend is LoadState.Loading}")
                loading.isVisible = it.source.append is LoadState.Loading
            }
        }
    }
}