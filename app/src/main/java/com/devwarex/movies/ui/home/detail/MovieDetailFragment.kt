package com.devwarex.movies.ui.home.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.devwarex.movies.data.ApiResource
import com.devwarex.movies.databinding.FragmentMovieDetailBinding
import com.devwarex.movies.ui.home.MoviesMainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private var _bindig: FragmentMovieDetailBinding? = null
    private val binding get() = _bindig!!
    private val mainViewModel: MoviesMainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bindig = FragmentMovieDetailBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel by viewModels<MovieDetailViewModel>()
        mainViewModel.movieId.observe(viewLifecycleOwner,viewModel::getMovie)
        viewModel.movie.observe(viewLifecycleOwner){
            when(it){
                is ApiResource.Success -> {
                    Log.e("movie",it.data.title)
                }
            }

        }
    }
}