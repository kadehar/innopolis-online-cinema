package com.github.kadehar.innopolis_online_cinema.data.api

import com.github.kadehar.innopolis_online_cinema.data.toDomain
import com.github.kadehar.innopolis_online_cinema.domain.model.Movie

class MoviesRepositoryImpl(private val moviesRemoteSource: MoviesRemoteSource) :
    MoviesRepository {
    override suspend fun fetchMovies(): List<Movie> {
        return moviesRemoteSource.fetchMovies().results.map { result -> result.toDomain() }
    }
}