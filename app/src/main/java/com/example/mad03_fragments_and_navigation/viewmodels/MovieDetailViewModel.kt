package com.example.mad03_fragments_and_navigation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mad03_fragments_and_navigation.models.Movie
import com.example.mad03_fragments_and_navigation.repositories.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailViewModel(
    private val repository: MovieRepository
) : ViewModel() {


    init {

    }

    fun onCreate(movie: Movie?) {
        viewModelScope.launch {
            if (movie != null) {
                repository.create(movie)
            }
        }
    }

}