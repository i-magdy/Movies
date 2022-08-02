package com.devwarex.movies.ui.home.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.devwarex.movies.R
import com.devwarex.movies.adapter.MovieAdapterListener
import com.devwarex.movies.adapter.MoviesAdapter
import com.devwarex.movies.util.EndPoint.GENRE_ID_KEY
import com.devwarex.movies.databinding.FragmentMoviesBinding
import com.devwarex.movies.ui.home.MoviesMainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesFragment: Fragment(),MovieAdapterListener {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel: MoviesMainViewModel by activityViewModels()

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
        val adapter = MoviesAdapter(this)
        binding.contentMovies.moviesRecyclerView.adapter = adapter
        arguments?.takeIf { it.containsKey(GENRE_ID_KEY) }?.apply {
            lifecycleScope.launchWhenCreated {
                launch { viewModel.getMoviesByGenre(genreId = getInt(GENRE_ID_KEY)).collectLatest { adapter.submitData(it) } }
            }
        }
        lifecycleScope.launchWhenCreated {
            launch { adapter.loadStateFlow.collect{
                binding.contentMovies.moviesAppendProgress.isVisible = it.source.append is LoadState.Loading
            } }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onMovieClick(movieId: Int) {
        mainViewModel.setMovieId(movieId)
        findNavController().navigate(R.id.action_main_fragment_to_movie_detail_fragment)
    }
}