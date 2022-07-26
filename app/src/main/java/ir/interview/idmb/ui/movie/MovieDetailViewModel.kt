package ir.interview.idmb.ui.movie

import androidx.lifecycle.viewModelScope
import ir.interview.idmb.data.repository.model.DataResult
import ir.interview.idmb.data.repository.model.MovieRepository
import ir.interview.idmb.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class MovieDetailViewModel(private val movieRepository: MovieRepository) :
    BaseViewModel<MovieDetailState, MovieDetailEvent, MovieDetailEffect>() {

    override fun createInitialState(): MovieDetailState = MovieDetailState.IDLE

    override fun handleEvents() {
        viewModelScope.launch {
            _event.consumeAsFlow().collect { event ->
                when (event) {
                    is MovieDetailEvent.GetMovie -> {
                        getMovie(event.movieId)
                    }
                }
            }
        }
    }

    private fun getMovie(movieId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            movieRepository.getMovie(movieId).collect { dataResult ->
                when (dataResult) {
                    is DataResult.Loading -> _state.emit(MovieDetailState.Loading)
                    is DataResult.NoData -> _state.emit(MovieDetailState.NoData)
                    is DataResult.NoInternet -> _state.emit(MovieDetailState.NoInternet)
                    is DataResult.Error -> _state.emit(MovieDetailState.Error(dataResult.message))
                    is DataResult.Data -> manageMovieDetailData(dataResult.data)
                }
            }
        }
    }

    private suspend fun manageMovieDetailData(movieDetail: MovieDetail) {
        val list = mutableListOf<MovieDetailItem>()
        list.add(MovieDetailItem(0, "Title", movieDetail.title))
        list.add(MovieDetailItem(1, "Year", movieDetail.year))
        list.add(MovieDetailItem(2, "Rated", movieDetail.rated))
        list.add(MovieDetailItem(3, "Released", movieDetail.released))
        list.add(MovieDetailItem(4, "Runtime", movieDetail.runtime))
        list.add(MovieDetailItem(5, "Genre", movieDetail.genre))
        list.add(MovieDetailItem(6, "imdbRating", movieDetail.imdbRating))
        list.add(MovieDetailItem(7, "imdbVotes", movieDetail.imdbVotes))
        list.add(MovieDetailItem(8, "Director", movieDetail.director))
        list.add(MovieDetailItem(9, "Writer", movieDetail.writer))
        list.add(MovieDetailItem(10, "Actors", movieDetail.actors))
        list.add(MovieDetailItem(11, "Plot", movieDetail.plot))
        list.add(MovieDetailItem(12, "Language", movieDetail.language))
        list.add(MovieDetailItem(13, "Country", movieDetail.country))
        list.add(MovieDetailItem(14, "Awards", movieDetail.awards))
        list.add(MovieDetailItem(15, "Metascore", movieDetail.metaScore))
        list.add(MovieDetailItem(16, "BoxOffice", movieDetail.boxOffice))
        _state.emit(MovieDetailState.Data(movieDetail, list))
    }

}