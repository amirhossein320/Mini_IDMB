package ir.interview.idmb.ui.movies

import ir.interview.idmb.ui.base.UiEvent

sealed class MovieEvent : UiEvent {

    data class GetMovies(val searchText: String = "batman") : MovieEvent()
    data class ShowMovie(val movieId: String) : MovieEvent()
}
