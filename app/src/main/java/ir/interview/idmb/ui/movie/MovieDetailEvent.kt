package ir.interview.idmb.ui.movie

import ir.interview.idmb.ui.base.UiEvent

sealed class MovieDetailEvent : UiEvent {

    data class GetMovie(val movieId: String) : MovieDetailEvent()
}
