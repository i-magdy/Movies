package com.devwarex.movies.ui.home.movies

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.devwarex.movies.adapter.MoviesAdapter
import com.devwarex.movies.api.EndPoint.GENRE_ID_KEY
import com.devwarex.movies.databinding.FragmentMoviesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesFragment: Fragment() {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoviesBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel by viewModels<MoviesViewModel>()
        val adapter = MoviesAdapter()
        binding.contentMovies.moviesRecyclerView.adapter = adapter
        lifecycleScope.launchWhenCreated {
            launch { viewModel.items.collectLatest { adapter.submitData(it) } }
            launch { adapter.loadStateFlow.collect{
                binding.contentMovies.moviesAppendProgress.isVisible = it.source.append is LoadState.Loading
            } }
        }
        arguments?.takeIf { it.containsKey(GENRE_ID_KEY) }?.apply {
            Log.e("view","${getInt(GENRE_ID_KEY,0)}")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}