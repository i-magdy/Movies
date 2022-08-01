package com.devwarex.movies.ui.home

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.mainToolbar)
        val navController = findNavController(R.id.nav_host_fragment_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
        //val viewModel: MovieView by viewModels()
        //viewModel.sync()

        /*val adapter = MoviesAdapter()
        re.adapter = adapter
        re.layoutManager = LinearLayoutManager(this)
        lifecycleScope.launchWhenCreated {
            viewModel.items.collectLatest {
               adapter.submitData(it)
            }
        }

        lifecycleScope.launchWhenCreated {
            adapter.loadStateFlow.collect{
                Log.e("loading","${it.source.prepend is LoadState.Loading}")
                loading.isVisible = it.source.append is LoadState.Loading
            }
        }*/
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_main_menu,menu)
        searchView = menu?.findItem(R.id.action_search)?.actionView as SearchView
        searchView?.queryHint = getString(R.string.search_by_name)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView?.apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                //TODO make a search
                findNavController(R.id.nav_host_fragment_main).navigate(R.id.search_fragment)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        searchView?.setOnCloseListener {
            //TODO clear search
            return@setOnCloseListener false
        }
        return true
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
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