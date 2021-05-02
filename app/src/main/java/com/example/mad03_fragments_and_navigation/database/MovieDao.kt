package com.example.mad03_fragments_and_navigation.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mad03_fragments_and_navigation.models.Movie

@Dao
interface MovieDao {

    @Insert
    suspend fun create(movie: Movie)

    @Update
    suspend fun update(movie: Movie)

    @Query("Delete from favorite_movies where id = :id")
    suspend fun delete(id: Long)

    @Query("DELETE FROM favorite_movies")
    suspend fun clearTable()

    @Query("SELECT * FROM favorite_movies")
    fun getAll(): LiveData<List<Movie>>


}