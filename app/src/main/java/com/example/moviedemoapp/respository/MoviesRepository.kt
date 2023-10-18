package com.example.moviedemoapp.respository

import com.example.moviedemoapp.api.MovieAPI
import javax.inject.Inject

/**
 * Repository class to get the movies
 */
class MoviesRepository @Inject constructor(private val apiMovies: MovieAPI) {

    suspend fun getMovies() = apiMovies.getMovies()

}