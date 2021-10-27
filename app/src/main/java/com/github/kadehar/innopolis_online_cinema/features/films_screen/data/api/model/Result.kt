package com.github.kadehar.innopolis_online_cinema.features.films_screen.data.api.model

import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("adult") val adult: Boolean,
    @SerializedName("genres") val genres: List<Genre>,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("title") val title: String,
    @SerializedName("video") val video: String,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int
)
