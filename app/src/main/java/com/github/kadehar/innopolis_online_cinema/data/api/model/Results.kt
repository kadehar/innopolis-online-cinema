package com.github.kadehar.innopolis_online_cinema.data.api.model

import com.google.gson.annotations.SerializedName

data class Results(
    @SerializedName("results") val results: List<Result>
)
