package com.github.kadehar.innopolis_online_cinema.data.api

import com.github.kadehar.innopolis_online_cinema.data.api.model.Results

class MoviesRemoteSource(private val moviesApi: MoviesApi) {
    suspend fun fetchMovies(): Results = moviesApi.fetchMovies()
}