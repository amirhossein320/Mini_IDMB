package ir.interview.idmb.ui.movies

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.LinearLayoutManager
import ir.interview.idmb.R
import ir.interview.idmb.databinding.FragmentMovieBinding
import ir.interview.idmb.ui.base.BaseFragment
import ir.interview.idmb.ui.movie.MovieDetailFragment
import ir.interview.idmb.utils.MOVIE_ID_KEY
import ir.interview.idmb.utils.gone
import ir.interview.idmb.utils.visible
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class MovieFragment :
    BaseFragment<FragmentMovieBinding>(FragmentMovieBinding::inflate) {

    private lateinit var viewModel: MovieViewModel
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory {
            initializer { MovieViewModel(getInjection().getMovieRepository()) }
        })[MovieViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.setEvent(MovieEvent.GetMovies())
        setupDetailRecycler()
        handleState()
        handleEffect()

    }

    private inline fun handleState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    when (state) {
                        is MovieState.IDLE -> {}
                        is MovieState.NoInternet -> showNoInternet()
                        is MovieState.Loading -> showLoading()
                        is MovieState.NoData -> showError(getString(R.string.no_data))
                        is MovieState.Error -> showError(errorMessage = state.message)
                        is MovieState.Movies -> showData(state.movies)
                        else -> {}
                    }
                }
            }
        }
    }

    private inline fun handleEffect() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.effect.consumeAsFlow().collect { effect ->

                }
            }
        }
    }

    private inline fun setupDetailRecycler() {
        movieAdapter = MovieAdapter().also {
            it.onItemClick = { movie ->
                navigateTo(MovieDetailFragment().apply {
                    arguments = Bundle().apply {
                        putString(MOVIE_ID_KEY, movie.imdbID)
                    }
                })
            }
        }
        binding.movieRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = movieAdapter
        }
    }

    private fun showData(movies: List<Movie>) {
        with(binding) {
            with(viewState) {
                imgMessage.gone()
                txtMessage.gone()
                progressBar.gone()
            }
            movieRecycler.visible()
            movieAdapter.submitList(movies)
        }
    }

    private fun showError(errorMessage: String) {
        with(binding) {
            with(viewState) {
                imgMessage.visible()
                txtMessage.apply {
                    visible()
                    text = errorMessage
                }
                progressBar.gone()
            }
            movieRecycler.gone()
        }
    }

    private fun showLoading() {
        with(binding) {
            with(viewState) {
                imgMessage.gone()
                txtMessage.gone()
                progressBar.visible()
            }
            movieRecycler.gone()
        }
    }
}