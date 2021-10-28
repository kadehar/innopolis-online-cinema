package com.github.kadehar.innopolis_online_cinema.features.films_screen.ui

import com.github.kadehar.innopolis_online_cinema.base.BaseViewModel
import com.github.kadehar.innopolis_online_cinema.base.Event
import com.github.kadehar.innopolis_online_cinema.base.SingleLiveEvent
import com.github.kadehar.innopolis_online_cinema.domain.MoviesInteractor

class MoviesViewModel(private val moviesInteractor: MoviesInteractor) :
    BaseViewModel<ViewState>() {
    val singleLiveEvent = SingleLiveEvent<SingleEvent>()

    init {
        processUiEvent(UiEvent.FetchMovies)
    }

    override fun initialViewState(): ViewState = ViewState(
        movies = emptyList(),
        errorMessage = null,
        isLoading = false
    )

    override suspend fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {
            is UiEvent.FetchMovies -> {
                processDataEvent(DataEvent.OnFetching)
                moviesInteractor.fetchMovies().fold(
                    onSuccess = { movies ->
                        processDataEvent(DataEvent.SuccessMoviesRequest(movies = movies))
                    },
                    onError = { exception ->
                        processDataEvent(
                            DataEvent.ErrorMoviesRequest(
                                exception.localizedMessage ?: ""
                            )
                        )
                    }
                )
            }
            is UiEvent.OnPosterClick -> {
                singleLiveEvent.value = SingleEvent.OpenMovieCard(event.movie)
            }
            is DataEvent.OnFetching -> {
                return previousState.copy(isLoading = !previousState.isLoading)
            }
            is DataEvent.SuccessMoviesRequest -> {
                return previousState.copy(
                    movies = event.movies,
                    isLoading = false,
                    errorMessage = null
                )
            }
            is DataEvent.ErrorMoviesRequest -> {
                return previousState.copy(
                    isLoading = false,
                    errorMessage = event.errorMessage
                )
            }
        }
        return null
    }
}