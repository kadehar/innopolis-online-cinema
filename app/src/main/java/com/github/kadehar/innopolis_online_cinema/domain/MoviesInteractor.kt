package com.github.kadehar.innopolis_online_cinema.domain

import com.github.kadehar.innopolis_online_cinema.base.attempt
import com.github.kadehar.innopolis_online_cinema.data.api.MoviesRepository

class MoviesInteractor(private val moviesRepository: MoviesRepository) {
    suspend fun fetchMovies() = attempt {
        moviesRepository.fetchMovies()
    }
}