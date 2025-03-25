package com.example.movieapp.model.data_source.remote

import android.util.Log
import com.example.movieapp.utils.response.NetworkResponse
import com.example.movieapp.model.data_source.remote.NetworkClient.makeRequest
import com.example.movieapp.model.data_source.remote.dto.Movie
import com.example.movieapp.model.data_source.remote.dto.Search
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiClient @Inject constructor() {
    private val BASE_URL = "https://www.omdbapi.com/"
    private val API_KEY = "a46db2e7"

    suspend fun searchMovies(query: String): NetworkResponse<Search> {
        val url = "$BASE_URL?s=$query&apikey=$API_KEY"
        Log.d("searchMovies", "searchMovies: $query")
        val response: Search? = makeRequest(url, Search::class.java)
        return response?.let {
            NetworkResponse.Success(it)
        } ?: NetworkResponse.Failure("Failed to fetch movies")
    }

    suspend fun getMovieDetails(imdbId: String): NetworkResponse<Movie> {
        val url = "$BASE_URL?i=$imdbId&apikey=$API_KEY"
        return makeRequest(url, Movie::class.java)?.let {
            NetworkResponse.Success(it)
        } ?: NetworkResponse.Failure("Failed to fetch movie details")
    }
}