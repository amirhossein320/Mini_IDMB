package ir.interview.idmb.ui.movies

import ir.interview.idmb.ui.base.UiState

sealed class MovieState : UiState {

    object IDLE : MovieState()
    object Loading : MovieState()
    object NoInternet : MovieState()
    object NoData : MovieState()
    data class Error(val message: String) : MovieState()
    data class Movies(val movies: List<Movie>) : MovieState()
}
