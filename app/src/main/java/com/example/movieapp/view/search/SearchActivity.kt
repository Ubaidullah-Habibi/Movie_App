package com.example.movieapp.view.search

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapp.R
import com.example.movieapp.utils.makeGone
import com.example.movieapp.utils.makeVisible
import com.example.movieapp.utils.setOnSafeClickListener
import com.example.movieapp.databinding.ActivitySearchBinding
import com.example.movieapp.view.base.BaseActivity
import com.example.movieapp.view.detail.MovieDetailActivity
import com.example.movieapp.view.search.components.SearchMovieAdapter
import com.example.movieapp.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : BaseActivity() {
    private lateinit var binding:ActivitySearchBinding
    private val viewModel: SearchViewModel by viewModels()
    private lateinit var movieAdapter: SearchMovieAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        with(binding){
            toolbarSearch.apply {
                tvTitle.text = getString(R.string.search_movies)
                ivBack.makeVisible()
                ivBack.setOnClickListener {
                    finish()
                }
            }
            // Setup RecyclerView with GridLayoutManager (2 columns)
            movieAdapter = SearchMovieAdapter(mContext){ movie ->
                val movieDetailIntent = Intent(mContext, MovieDetailActivity::class.java)
                    .putExtra("imdbID", movie.imdbID)
                startActivity(movieDetailIntent)
            }
            recyclerSearchResults.layoutManager = GridLayoutManager(mContext, 2)
            recyclerSearchResults.adapter = movieAdapter
            cvSearch.apply {
                val hint = getString(R.string.search_movies)
                etSearchList.hint = hint
                tvSearch.hint = hint
                setUpTextViewSearch()
                etSearchList.setOnEditorActionListener { v, actionId, event ->
                    if (actionId == EditorInfo.IME_ACTION_DONE || event?.keyCode == KeyEvent.KEYCODE_ENTER) {
                        searchMovies()
                        true // Return true to indicate the event was handled
                    } else {
                        false
                    }
                }

                etSearchList.addTextChangedListener { text ->
                    if (text.toString().trim().isEmpty()) {
                        ivCancel.makeGone()
                    } else {
                        ivCancel.makeVisible()
                    }
                }
            }
            btnSearch.setOnSafeClickListener {
                searchMovies()
            }
            // Observe Search Results and update UI
            viewModel.searchDetails.observe(this@SearchActivity) { response ->
                val results = response?.Search ?: emptyList()
                movieAdapter.submitList(results)
                layoutProgress.makeGone()
                if (results.isNotEmpty()) {
                    searchEmptyState.makeGone()
                    recyclerSearchResults.makeVisible()
                } else {
                    searchEmptyState.makeVisible()
                    recyclerSearchResults.makeGone()
                }
            }
        }

    }
    private fun searchMovies(){
        with(binding){
            layoutProgress.makeVisible()
            searchEmptyState.makeGone()
            val query = cvSearch.etSearchList.text.toString().trim()
            if (query.isNotEmpty()) {
                viewModel.searchMovies(query)
                inputController.hideKeyboard(cvSearch.etSearchList)
            } else {
                Toast.makeText(
                    mContext,
                    getString(R.string.kindly_enter_some_movie_name_to_search),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
    private fun setUpTextViewSearch(){
        with(binding.cvSearch){
                tvSearch.makeGone()
                ivSearch.makeVisible()
                etSearchList.makeVisible()
                etSearchList.requestFocus()
                inputController.showKeyboard(etSearchList)
            ivCancel.setOnClickListener {
                if (etSearchList.text.toString().trim().isNotEmpty()) {
                    etSearchList.setText("")
                    ivCancel.makeGone()
                    return@setOnClickListener
                }
            }
        }
    }

    override fun handleBackPressed() {
        finish()
    }
}