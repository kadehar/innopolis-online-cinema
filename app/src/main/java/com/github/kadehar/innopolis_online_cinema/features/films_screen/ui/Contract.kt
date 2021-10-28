package com.github.kadehar.innopolis_online_cinema.features.films_screen.ui

import com.github.kadehar.innopolis_online_cinema.base.Event
import com.github.kadehar.innopolis_online_cinema.domain.model.Movie

data class ViewState(
    val movies: List<Movie>,
    val errorMessage: String?,
    val isLoading: Boolean
) {
    val isInErrorState: Boolean = errorMessage != null
}

sealed class UiEvent : Event {
    object FetchMovies : UiEvent()
    data class OnPosterClick(val movie: Movie) : UiEvent()
}

sealed class DataEvent : Event {
    object OnFetching : DataEvent()
    data class SuccessMoviesRequest(val movies: List<Movie>) : DataEvent()
    data class ErrorMoviesRequest(val errorMessage: String) : DataEvent()
}

sealed class SingleEvent : Event {
    data class OpenMovieCard(val movie: Movie) : SingleEvent()
}