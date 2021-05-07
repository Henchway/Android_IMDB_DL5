package com.example.mad03_fragments_and_navigation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mad03_fragments_and_navigation.models.Movie
import com.example.mad03_fragments_and_navigation.repositories.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieFavoritesViewModel(
    private val repository: MovieRepository
) : ViewModel() {

    lateinit var selectedMovie: Movie
    val favorites = repository.getAll()

    fun onUpdate(movie: Movie) {
        viewModelScope.launch {
            repository.update(movie = movie)
        }
    }

    fun onDelete(id: Long) {
        viewModelScope.launch {
            repository.delete(movieId = id)
        }
    }

    fun onClear() {
        viewModelScope.launch {
            repository.clearTable()
        }
    }

}