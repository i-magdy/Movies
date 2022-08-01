package com.devwarex.movies.ui.home.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.devwarex.movies.databinding.FragmentSearchMoviesBinding

class SearchMoviesFragment : Fragment() {


    private lateinit var binding: FragmentSearchMoviesBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchMoviesBinding.inflate(layoutInflater,container,false)

        return binding.root
    }
}