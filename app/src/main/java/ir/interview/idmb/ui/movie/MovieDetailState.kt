package ir.interview.idmb.ui.movie

import ir.interview.idmb.ui.base.UiState

sealed class MovieDetailState : UiState {

    object IDLE : MovieDetailState()
    object Loading : MovieDetailState()
    object NoInternet : MovieDetailState()
    object NoData : MovieDetailState()
    data class Error(val message: String) : MovieDetailState()
    data class Data(
        val movieDetail: MovieDetail,
        val movieDetailItems: List<MovieDetailItem>
    ) : MovieDetailState()
}
