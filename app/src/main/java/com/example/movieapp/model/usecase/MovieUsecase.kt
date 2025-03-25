package com.example.movieapp.model.usecase

import com.example.movieapp.model.repository.MovieRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieUsecase @Inject constructor(private val movieRepository: MovieRepository) {
    suspend fun searchMovies(query: String) = movieRepository.searchMovies(query)
    suspend fun movieDetails(movieId: String) = movieRepository.movieDetails(movieId)
}