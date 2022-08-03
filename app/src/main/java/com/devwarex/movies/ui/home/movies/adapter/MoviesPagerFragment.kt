package com.devwarex.movies.ui.home.movies.adapter

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.devwarex.movies.R
import com.devwarex.movies.data.ApiResource
import com.devwarex.movies.databinding.FragmentMoviesPagerBinding
import com.devwarex.movies.model.Genre
import com.devwarex.movies.ui.home.MoviesMainViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesPagerFragment : Fragment() {

    private var _binding: FragmentMoviesPagerBinding? = null
    private val binding get() = _binding!!
    private var searchView: SearchView? = null
    private var menuItem: MenuItem? = null
    private val mainViewModel by activityViewModels<MoviesMainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoviesPagerBinding.inflate(layoutInflater,container,false)
        val adapter = MoviesPagerAdapter(this)
        val viewModel: GenresViewModel by viewModels()
        viewModel.getGenres()
        binding.genresPager.adapter = adapter
        viewModel.genres.observe(viewLifecycleOwner){
            when(it){
                is ApiResource.Success -> {
                    binding.genreTabs.visibility = View.VISIBLE
                    val genres = addGeneralTabToGenres(genres = it.data.genres)
                    adapter.setGenres(genres)
                    showViewTabs(
                        tabLayout = binding.genreTabs,
                        view = binding.genresPager,
                        list = genres
                    )
                }
                is ApiResource.Error -> Toast.makeText(context,it.message,Toast.LENGTH_LONG).show()
                is ApiResource.Loading -> { binding.genreTabs.visibility = View.GONE }
            }
        }
        return binding.root
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_main_menu,menu)
        this.menuItem = menu.findItem(R.id.action_search)
        searchView = menuItem?.actionView as SearchView
        searchView?.queryHint = getString(R.string.search_by_name)
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (query.isNotBlank()){
                    mainViewModel.setQuery(query)
                    findNavController().navigate(R.id.action_main_fragment_to_search_fragment)
                }
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
    }

    private fun showViewTabs(tabLayout: TabLayout,view: ViewPager2,list: List<Genre>){
        TabLayoutMediator(tabLayout,view){tab,position ->
            tab.text = list[position].name
        }.attach()
    }

    private fun addGeneralTabToGenres(genres: List<Genre>): List<Genre>{
        val list = ArrayList<Genre>()
        list.add(
            Genre(
                id = -1,
                name = getString(R.string.popular_title)
            )
        )
        list.addAll(genres)
        return list;
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}