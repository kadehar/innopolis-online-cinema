package com.github.kadehar.innopolis_online_cinema.features.films_screen.data.api.model

import com.google.gson.annotations.SerializedName

data class Results(
    @SerializedName("results") val results: List<Result>
)
