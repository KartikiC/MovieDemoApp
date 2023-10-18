package com.example.moviedemoapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviedemoapp.models.Result
import com.example.moviedemoapp.respository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val repository: MoviesRepository) : ViewModel() {

    private val _response = MutableLiveData<List<Result>>()
    val responseMovies: LiveData<List<Result>>
        get() = _response

    init {
        getAllMovies()
    }

    /**
     * Get all movies Business logic and post value through Livedata
     */
    private fun getAllMovies() =
        viewModelScope.launch {
            repository.getMovies().let { response ->
                if (response.isSuccessful) {
                    _response.postValue(response.body()?.results)
                } else {
                    Log.d("tag", "get movies Error: ${response.code()}")
                }
            }
        }
}