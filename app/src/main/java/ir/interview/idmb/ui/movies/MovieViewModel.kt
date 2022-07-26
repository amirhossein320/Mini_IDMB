package ir.interview.idmb.ui.movies

import androidx.lifecycle.viewModelScope
import ir.interview.idmb.data.repository.model.DataResult
import ir.interview.idmb.data.repository.model.MovieRepository
import ir.interview.idmb.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class MovieViewModel(private val movieRepository: MovieRepository) :
    BaseViewModel<MovieState, MovieEvent, MovieEffect>() {

    override fun createInitialState(): MovieState = MovieState.IDLE

    override fun handleEvents() {
        viewModelScope.launch {
            _event.consumeAsFlow().collect { event ->
                when (event) {
                    is MovieEvent.GetMovies -> {
                        getMovies(event.movieId)
                    }
                    is MovieEvent.ShowMovie -> {
                        _effect.send(MovieEffect.ShowMovie(event.movieId))
                    }
                }
            }
        }
    }

    private fun getMovies(searchText: String = "batman") {
        viewModelScope.launch(Dispatchers.IO) {
            movieRepository.getMovies(searchText).collect { dataResult ->
                when (dataResult) {
                    is DataResult.Loading -> _state.emit(MovieState.Loading)
                    is DataResult.NoData -> _state.emit(MovieState.NoData)
                    is DataResult.NoInternet -> _state.emit(MovieState.NoInternet)
                    is DataResult.Error -> _state.emit(MovieState.Error(dataResult.message))
                    is DataResult.Data -> _state.emit(MovieState.Movies(dataResult.data))
                }
            }
        }
    }


}