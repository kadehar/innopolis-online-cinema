package com.github.kadehar.innopolis_online_cinema.domain.model

data class Movie(
    val genres: List<MovieGenre>,
    val originalTitle: String,
    val overview: String,
    val releaseDate: String,
    val posterPath: String,
    val title: String,
    val video: String,
    val voteAverage: Double
)
