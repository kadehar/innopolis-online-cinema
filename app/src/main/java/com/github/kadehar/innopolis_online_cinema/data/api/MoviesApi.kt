package com.github.kadehar.innopolis_online_cinema.data.api

import com.github.kadehar.innopolis_online_cinema.base.consts.Constants.BASE_MOVIES_PATH
import com.github.kadehar.innopolis_online_cinema.base.consts.Constants.CACHE_CONTROL_HEADER
import com.github.kadehar.innopolis_online_cinema.base.consts.Constants.CACHE_CONTROL_NO_CACHE
import com.github.kadehar.innopolis_online_cinema.data.api.model.Results
import retrofit2.http.GET
import retrofit2.http.Headers

interface MoviesApi {
    @GET(BASE_MOVIES_PATH)
    @Headers("$CACHE_CONTROL_HEADER: $CACHE_CONTROL_NO_CACHE")
    suspend fun fetchMovies(): Results
}