package ir.interview.idmb.ui.movies

import ir.interview.idmb.ui.base.UiEffect

sealed class MovieEffect : UiEffect{

    data class ShowMovie(val movieId: String) : MovieEffect()

}
