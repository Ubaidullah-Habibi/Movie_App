package com.example.movieapp.view.search.components

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.utils.applyGradient
import com.example.movieapp.utils.setOnSafeClickListener
import com.example.movieapp.model.data_source.remote.dto.Movie
import com.example.movieapp.databinding.ItemMovieBinding
class SearchMovieAdapter(private val mContext: Activity, private val onItemClick: (Movie) -> Unit) :
    ListAdapter<Movie, SearchMovieAdapter.MovieViewHolder>(DiffCallback()) {
    var gradientColor1 = ContextCompat.getColor(mContext, R.color.gradient_color_1)
    var gradientColor2 = ContextCompat.getColor(mContext, R.color.gradient_color_2)


    // Creates a ViewHolder when needed
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    // Binds data to ViewHolder
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    // DiffUtil for efficient updates
    class DiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem.imdbID == newItem.imdbID // Check if movies are the same based on IMDb ID

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem == newItem // Check if movie content has changed
    }

    // ViewHolder class to hold UI components for each movie item
    inner class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
            init {
                //clicking on movie item
                itemView.setOnSafeClickListener {
                    val position = adapterPosition
                    if(position != -1){
                        val movie = getItem(position)
                        onItemClick(movie)
                    }
                }
            }
        // Bind data to UI components
        fun bind(movie: Movie) {
            with(binding){
                movieTitle.text = movie.Title
                movieTitle.applyGradient(intArrayOf(gradientColor1,gradientColor2))
                movieYear.text = "Year: ${movie.Year}"

                // Load movie poster image using Glide
                Glide.with(root.context)
                    .load(movie.Poster)
                    .placeholder(R.drawable.placeholder) // Show a placeholder while loading
                    .into(moviePoster)
            }
        }
    }
}


