package com.example.moviedemoapp.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviedemoapp.adapter.MoviesAdapter
import com.example.moviedemoapp.databinding.ActivityMoviesBinding
import com.example.moviedemoapp.viewmodel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesActivity : ComponentActivity() {

    private lateinit var binding: ActivityMoviesBinding
    private val moviesViewModel: MoviesViewModel by viewModels()
    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        moviesAdapter = MoviesAdapter()

        binding.movieRecyclerView.apply {
            adapter = moviesAdapter
            layoutManager = LinearLayoutManager(
                this@MoviesActivity, LinearLayoutManager.VERTICAL,
                false
            )
            setHasFixedSize(true)
        }

        moviesViewModel.responseMovies.observe(this) { moviesList ->
            moviesAdapter.moviesList = moviesList

        }
        moviesAdapter.onItemClick = {
            val intent = Intent(this, MovieDetailsActivity::class.java)
            intent.putExtra("movie", it)
            startActivity(intent)
        }
    }
}