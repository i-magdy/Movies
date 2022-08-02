package com.devwarex.movies.ui.home.detail.adapter

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.devwarex.movies.model.Image
import com.devwarex.movies.util.EndPoint

class ImagePagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    private var images: List<Image> = listOf()
    override fun getItemCount(): Int = images.size

    override fun createFragment(position: Int): Fragment {
        val fa = MovieImageFragment()
        fa.arguments = Bundle().apply {
            putString(EndPoint.IMAGE_PATH_KEY,images[position].file_path)
        }
        return fa
    }


    @SuppressLint("NotifyDataSetChanged")
    fun setImages(list: List<Image>){
        images = list
        notifyDataSetChanged()
    }
}