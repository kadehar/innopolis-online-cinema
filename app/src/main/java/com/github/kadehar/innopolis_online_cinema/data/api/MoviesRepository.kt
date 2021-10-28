package com.github.kadehar.innopolis_online_cinema.data.api

import com.github.kadehar.innopolis_online_cinema.domain.model.Movie

interface MoviesRepository {
    suspend fun fetchMovies(): List<Movie>
}