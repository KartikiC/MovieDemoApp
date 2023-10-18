package com.example.moviedemoapp.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.moviedemoapp.databinding.ActivityMovieDetailsBinding
import com.example.moviedemoapp.models.Result
import com.squareup.picasso.Picasso

class MovieDetailsActivity : ComponentActivity() {

    private lateinit var binding: ActivityMovieDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movie = intent.getParcelableExtra<Result>("movie")

        if (movie != null) {
            binding.apply {
                movieNameTv.text = movie.title
                Picasso.get().load("https://image.tmdb.org/t/p/original" + movie.poster_path)
                    .into(moviePosterIv)
                ratingTv.text = movie.vote_average.toString()
                releaseDateTv.text = movie.release_date
                overviewTv.text = movie.overview
            }
        }
    }
}