package com.example.moviedemoapp.api

import com.example.moviedemoapp.models.MovieResponse
import com.example.moviedemoapp.helper.Constants
import retrofit2.Response
import retrofit2.http.GET

/**
 * API Interface to declare Get request
 */
interface MovieAPI {

    @GET(Constants.POPULAR_END_POINT)
    suspend fun getMovies(): Response<MovieResponse>
}