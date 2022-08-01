package com.devwarex.movies.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.mainToolbar)
        val navController = findNavController(R.id.nav_host_fragment_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
        val viewModel: MovieView by viewModels()
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
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}