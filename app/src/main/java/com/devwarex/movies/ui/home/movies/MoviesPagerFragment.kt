package com.devwarex.movies.ui.home.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.devwarex.movies.databinding.FragmentMoviesPagerBinding

class MoviesPagerFragment : Fragment() {

    private lateinit var binding: FragmentMoviesPagerBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoviesPagerBinding.inflate(layoutInflater,container,false)
        return binding.root
    }
}