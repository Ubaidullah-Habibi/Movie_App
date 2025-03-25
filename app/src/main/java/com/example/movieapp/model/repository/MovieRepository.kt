package com.example.movieapp.model.repository

import com.example.movieapp.utils.response.NetworkResponse
import com.example.movieapp.model.data_source.remote.dto.Movie
import com.example.movieapp.model.data_source.remote.dto.Search


interface MovieRepository {
    suspend fun searchMovies(query: String): NetworkResponse<Search>
    suspend fun movieDetails(movieId: String): NetworkResponse<Movie>
}