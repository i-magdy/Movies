package com.devwarex.movies.ui.home.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.devwarex.movies.R
import com.devwarex.movies.adapter.CastAdapter
import com.devwarex.movies.data.ApiResource
import com.devwarex.movies.databinding.FragmentMovieDetailBinding
import com.devwarex.movies.model.MovieDetail
import com.devwarex.movies.ui.home.MoviesMainViewModel
import com.devwarex.movies.ui.home.detail.adapter.ImagePagerAdapter
import com.devwarex.movies.util.ZoomOutTransformation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel: MoviesMainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieDetailBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel by viewModels<MovieDetailViewModel>()
        mainViewModel.movieId.observe(viewLifecycleOwner,viewModel::getMovie)
        val adapter = ImagePagerAdapter(this)
        binding.movieContent.movieImagesPager.adapter = adapter
        binding.movieContent.movieImagesPager.setPageTransformer(ZoomOutTransformation())
        val castAdapter = CastAdapter()
        binding.movieContent.movieCastRecycler.adapter = castAdapter
        binding.movieContent.movieCastRecycler.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)

        viewModel.images.observe(viewLifecycleOwner){
            adapter.setImages(it.backdrops)
        }

        viewModel.movie.observe(viewLifecycleOwner){
            when(it){
                is ApiResource.Error -> {
                    binding.movieContent.mainLayout.visibility = View.GONE
                    binding.detailMovieLoading.visibility = View.GONE
                }
                is ApiResource.Success -> {
                    updateUi(it.data)
                }

                is ApiResource.Loading -> {
                    binding.movieContent.mainLayout.visibility = View.GONE
                    binding.detailMovieLoading.visibility = View.VISIBLE
                }
            }
        }

        viewModel.credits.observe(viewLifecycleOwner){
            when(it){
                is ApiResource.Error -> {
                    binding.movieContent.detailCastTitle.visibility = View.GONE
                    binding.movieContent.movieCastRecycler.visibility = View.GONE
                }
                is ApiResource.Success -> {
                    binding.movieContent.detailCastTitle.visibility = View.VISIBLE
                    binding.movieContent.movieCastRecycler.visibility = View.VISIBLE
                    if (it.data.cast.isNullOrEmpty()){
                        binding.movieContent.detailCastTitle.visibility = View.GONE
                        binding.movieContent.movieCastRecycler.visibility = View.GONE
                    }
                    castAdapter.setCasts(it.data.cast)
                }

                is ApiResource.Loading -> {
                    binding.movieContent.detailCastTitle.visibility = View.GONE
                    binding.movieContent.movieCastRecycler.visibility = View.GONE
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateUi(movie: MovieDetail){
        mainViewModel.setMovieTitle(movie.title)
        binding.movieContent.mainLayout.visibility = View.VISIBLE
        binding.detailMovieLoading.visibility = View.GONE
        binding.movieContent.detailMovieOriginalTitleTv.text = movie.original_title
        binding.movieContent.detailMovieRating.rating = movie.vote_average.toFloat()
        binding.movieContent.detailMovieRatingTv.text = movie.vote_average.toString()
        binding.movieContent.detailMovieRatingCountTv.text = "(${movie.vote_count})"
        binding.movieContent.detailMovieOriginalTv.text = if (movie.production_countries.isNullOrEmpty())"" else movie.production_countries[0].iso_3166_1
        binding.movieContent.detailMovieReleaseTv.text = movie.release_date
        binding.movieContent.detailMovieOverviewTv.text = movie.overview
        binding.movieContent.detailMovieDuration.text = "${movie.runtime} ${getString(R.string.time_unit)}"
        binding.movieContent.imageGuideLayout.animate().setStartDelay(1200).alpha(0f).start()
    }
}