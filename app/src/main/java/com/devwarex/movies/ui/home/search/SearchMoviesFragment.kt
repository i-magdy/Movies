package com.devwarex.movies.ui.home.search

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
import com.devwarex.movies.databinding.FragmentSearchMoviesBinding
import com.devwarex.movies.ui.home.MoviesMainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchMoviesFragment : Fragment(), MovieAdapterListener {


    private var _binding: FragmentSearchMoviesBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel: MoviesMainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchMoviesBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel by viewModels<SearchMoviesViewModel>()
        val adapter = MoviesAdapter(this)
        binding.contentMovies.moviesRecyclerView.adapter = adapter
        mainViewModel.searchQuery.observe(viewLifecycleOwner){
            lifecycleScope.launchWhenCreated {
                viewModel.getMovies(it).collectLatest { data -> adapter.submitData(data) }
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
        findNavController().navigate(R.id.action_open_movie_detail)
    }
}