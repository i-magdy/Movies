package com.devwarex.movies.ui.home

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.devwarex.movies.R
import com.devwarex.movies.databinding.ActivityMoviesBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MoviesActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMoviesBinding
    private var searchView: SearchView? = null
    private var menuItem: MenuItem? = null
    private val viewModel: MoviesMainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.mainToolbar)
        val navController = findNavController(R.id.nav_host_fragment_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navController.addOnDestinationChangedListener { controller, destination, arguments -> hideSearchView() }
        viewModel.title.observe(this){ supportActionBar?.title = it }
    }


    override fun onResume() {
        super.onResume()
        hideSearchView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_main_menu,menu)
        this.menuItem = menu?.findItem(R.id.action_search)
        searchView = menuItem?.actionView as SearchView
        searchView?.queryHint = getString(R.string.search_by_name)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView?.apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (query.isNotBlank()){
                    viewModel.setQuery(query)
                    findNavController(R.id.nav_host_fragment_main).navigate(R.id.action_main_fragment_to_search_fragment)
                }
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        return true
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    private fun hideSearchView(){
        if (searchView != null) {
            menuItem?.isVisible = false
            searchView?.setQuery(null, false)
            searchView?.isIconified = true
        }
    }
    override fun onBackPressed() {
        if (searchView != null){
            if (searchView!!.isIconified){
                super.onBackPressed()
            }else{
                searchView?.isIconified = true
            }
        }else{
            super.onBackPressed()
        }
    }
}