package com.example.movieapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.utils.response.NetworkResponse
import com.example.movieapp.model.data_source.remote.dto.Search
import com.example.movieapp.model.usecase.MovieUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val usecase: MovieUsecase) : ViewModel() {
    private val _searchDetails = MutableLiveData<Search?>()
    val searchDetails: LiveData<Search?> = _searchDetails

    // Call this to perform the search
    fun searchMovies(query: String) {
        viewModelScope.launch {
            try {
                Log.d("searchMovies", "searchMovies: $query")
                val response = usecase.searchMovies(query)
                when (response) {
                    is NetworkResponse.Failure -> {
                        _searchDetails.postValue(null)
                    }

                    is NetworkResponse.Idle -> Unit
                    is NetworkResponse.Loading -> Unit
                    is NetworkResponse.Success -> {
                        _searchDetails.postValue(response.data)
                    }
                }
                Log.d("Search", "searchMovies: $response ")
            } catch (e: Exception) {
                // Handle error – you might want to post an empty SearchResponse or log the error
                e.printStackTrace()
            }
        }
    }
}