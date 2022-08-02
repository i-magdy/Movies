package com.devwarex.movies.ui.home.detail.adapter

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.devwarex.movies.R
import com.devwarex.movies.util.EndPoint

class MovieImageFragment : Fragment(R.layout.layout_movie_image) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val image = view.findViewById<ImageView>(R.id.detail_movie_image)
        arguments?.takeIf { it.containsKey(EndPoint.IMAGE_PATH_KEY) }?.apply {
            val url: String = EndPoint.IMAGE_BASE_URL+getString(EndPoint.IMAGE_PATH_KEY)
            Glide.with(requireContext())
                .load(url)
                .into(image)
        }

    }
}