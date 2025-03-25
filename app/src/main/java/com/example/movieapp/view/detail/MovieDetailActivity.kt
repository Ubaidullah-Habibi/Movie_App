package com.example.movieapp.view.detail

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.utils.applyGradient
import com.example.movieapp.utils.makeGone
import com.example.movieapp.utils.makeVisible
import com.example.movieapp.model.data_source.remote.dto.Movie
import com.example.movieapp.databinding.ActivityMovieDetailBinding
import com.example.movieapp.view.base.BaseActivity
import com.example.movieapp.viewmodel.MovieDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailActivity : BaseActivity() {
    private lateinit var binding:ActivityMovieDetailBinding
    private val viewModel: MovieDetailViewModel by viewModels()
    private var imdbID: String? = null
    private var gradientColor1 = 0
    private var gradientColor2 = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        gradientColor1 = ContextCompat.getColor(mContext, R.color.gradient_color_1)
        gradientColor2 = ContextCompat.getColor(mContext, R.color.gradient_color_2)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        with(binding){
            toolbarMovieDetail.apply {
                tvTitle.text = getString(R.string.movie_detail)
                ivBack.makeVisible()
                ivBack.setOnClickListener {
                    finish()
                }
            }
        }
        val bundle = intent.extras
        if(bundle != null){
            imdbID = bundle.getString("imdbID")
            // Fetch movie details using the IMDb ID
            imdbID?.let { viewModel.fetchMovieDetails(it) }

            // Observe movie details and update UI when data is available
            viewModel.movieDetails.observe(this) { movie ->
                if (movie != null) {
                    updateUI(movie)
                }
            }
        }
    }
    private fun updateUI(movie: Movie) {
        with(binding){
            // Set movie details to UI components
            layoutProgress.makeGone()
            moviePoster.makeVisible()
            movieContent.makeVisible()
            movieTitle.text = movie.Title
            movieTitle.applyGradient(intArrayOf(gradientColor1,gradientColor2))
            movieGenre.text = movie.Genre
            moviePlot.text = movie.Plot
            movieRuntime.text = movie.Runtime
            movieActors.text = "Actors: ${movie.Actors}"
            movieYear.text = movie.Year
            movieCountry.text = "Country: ${movie.Country}"
            movieDirector.text = "Director: ${movie.Director}"
            movieRated.text = movie.Rated
            movieLanguage.text = "Language: ${movie.Language}"

            // Convert IMDb rating to a star rating (out of 5)
            val imdbRating = movie.imdbRating?.toFloatOrNull() ?: 0f
            val starRating = when {
                imdbRating >= 8 -> 5f
                imdbRating >= 7 -> 4f
                imdbRating >= 6 -> 3f
                imdbRating >= 5 -> 2f
                else -> 1f
            }
            movieRatingStars.rating = starRating

            // Load movie poster image using Glide with a placeholder
            Glide
                .with(this@MovieDetailActivity)
                .load(movie.Poster)
                .placeholder(R.drawable.placeholder)
                .into(moviePoster)
        }
    }

    override fun handleBackPressed() {
        finish()
    }
}