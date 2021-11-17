package com.github.kadehar.innopolis_online_cinema.data

import com.github.kadehar.innopolis_online_cinema.data.api.model.Genre
import com.github.kadehar.innopolis_online_cinema.data.api.model.Result
import com.github.kadehar.innopolis_online_cinema.domain.model.Movie
import com.github.kadehar.innopolis_online_cinema.domain.model.MovieGenre

fun Result.toDomain(): Movie {
    val genres: List<MovieGenre> = genres.map { genre -> genre.toDomain() }

    return Movie(
        genres = genres,
        originalTitle = originalTitle,
        overview = overview,
        releaseDate = releaseDate,
        posterPath = posterPath,
        title = title,
        video = video,
        voteAverage = voteAverage
    )
}

fun Genre.toDomain(): MovieGenre = MovieGenre(name = name)