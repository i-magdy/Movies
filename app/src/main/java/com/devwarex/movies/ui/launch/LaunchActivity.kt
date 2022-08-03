package com.devwarex.movies.ui.launch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.devwarex.movies.R
import com.devwarex.movies.ui.home.MoviesActivity
import kotlinx.coroutines.delay

class LaunchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)
        lifecycleScope.launchWhenCreated {
            delay(2000)
            startActivity(Intent(this@LaunchActivity,MoviesActivity::class.java))
            finish()
        }
    }
}