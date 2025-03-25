package com.example.movieapp.model.repository

import com.example.movieapp.utils.response.NetworkResponse
import com.example.movieapp.model.data_source.remote.ApiClient
import com.example.movieapp.model.data_source.remote.dto.Movie
import com.example.movieapp.model.data_source.remote.dto.Search
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepositoryImp @Inject constructor(
    private val apiClient: ApiClient
) : MovieRepository {

    override suspend fun searchMovies(query: String): NetworkResponse<Search> {
        return apiClient.searchMovies(query = query)
    }
    override suspend fun movieDetails(movieId: String): NetworkResponse<Movie> {
        return apiClient.getMovieDetails(movieId)
    }
}
