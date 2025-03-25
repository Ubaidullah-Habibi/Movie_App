package com.example.movieapp.model.data_source.remote.dto


data class Movie(
    val Title: String? = null,
    val Year: String? = null,
    val Poster: String? = null,
    val Rated: String? = null,
    val Director: String? = null,
    val Writer: String? = null,
    val Actors: String? = null,
    val Language: String? = null,
    val Country: String? = null,
    val Ratings: List<Rating>,
    val imdbRating: String? = null, // String in API
    val imdbID: String? = null,
    val Plot: String? = null,
    val Genre: String? = null,
    val Runtime: String? = null
)

data class Rating(
    val Source: String,
    val Value: String
)
