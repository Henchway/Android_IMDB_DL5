package com.example.mad03_fragments_and_navigation.repositories

import androidx.lifecycle.LiveData
import androidx.room.Dao
import com.example.mad03_fragments_and_navigation.database.MovieDao
import com.example.mad03_fragments_and_navigation.models.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class MovieRepository(private val movieDao: MovieDao) {

    suspend fun create(movie: Movie) {
        withContext(Dispatchers.IO) {
            movieDao.create(movie)
        }
    }

    fun getAll(): LiveData<List<Movie>> {
        return movieDao.getAll()
    }

    suspend fun update(movie: Movie) {
        withContext(Dispatchers.IO) {
            movieDao.update(movie = movie)
        }
    }

    suspend fun delete(movieId: Long) {
        withContext(Dispatchers.IO) {
            movieDao.delete(id = movieId)
        }
    }

    suspend fun clearTable() {
        withContext(Dispatchers.IO) {
            movieDao.clearTable()
        }
    }


    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(dao: MovieDao) =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(dao).also { instance = it }
            }
    }
}