package com.example.movieapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.utils.response.NetworkResponse
import com.example.movieapp.model.data_source.remote.dto.Movie
import com.example.movieapp.model.usecase.MovieUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val usecase: MovieUsecase) : ViewModel() {

    // LiveData to hold movie details
    private val _movieDetails = MutableLiveData<Movie?>()
    val movieDetails: LiveData<Movie?> get() = _movieDetails

    // Fetch movie details from repository using IMDb ID
    fun fetchMovieDetails(imdbID: String) {
        viewModelScope.launch {
            try {
                // Fetch movie details from API
                val response = usecase.movieDetails(imdbID)
                // Update LiveData with fetched movie details
                 when(response){
                     is NetworkResponse.Failure -> {
                         _movieDetails.postValue(null)
                     }
                     is NetworkResponse.Idle -> Unit
                     is NetworkResponse.Loading -> Unit
                     is NetworkResponse.Success -> {

                         _movieDetails.postValue(response.data)
                     }
                 }

                // Log response for debugging
                Log.d("Details", "fetchMovieDetails: $response ")
            } catch (e: Exception) {
                // In case of an error, set LiveData to null
                _movieDetails.postValue(null)
            }
        }
    }
}
