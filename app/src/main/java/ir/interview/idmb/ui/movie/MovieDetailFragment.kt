package ir.interview.idmb.ui.movie

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
import ir.interview.idmb.databinding.FragmentMovieDetailBinding
import ir.interview.idmb.ui.base.BaseFragment
import ir.interview.idmb.utils.gone
import ir.interview.idmb.utils.loadImage
import ir.interview.idmb.utils.visible
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class MovieDetailFragment :
    BaseFragment<FragmentMovieDetailBinding>(FragmentMovieDetailBinding::inflate) {

    private lateinit var viewModel: MovieDetailViewModel
    private lateinit var movieDetailAdapter: MovieDetailAdapter
    private var movieId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory {
            initializer { MovieDetailViewModel(getInjection().getMovieRepository()) }
        })[MovieDetailViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieId?.let {
            viewModel.setEvent(MovieDetailEvent.GetMovie(it))
        } ?: run { showError(getString(R.string.err_movie_not_found)) }

        setupDetailRecycler()
        handleState()
        handleEffect()

    }


    private inline fun handleState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    when (state) {
                        is MovieDetailState.IDLE -> {}
                        is MovieDetailState.NoInternet -> showNoInternet()
                        is MovieDetailState.Loading -> showLoading()
                        is MovieDetailState.NoData -> showError(getString(R.string.no_data))
                        is MovieDetailState.Error -> showError(errorMessage = state.message)
                        is MovieDetailState.Data -> showData(
                            state.movieDetail,
                            state.movieDetailItems
                        )
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
        movieDetailAdapter = MovieDetailAdapter()
        binding.movieDetailRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = movieDetailAdapter
        }
    }

    private fun showData(movieDetail: MovieDetail, movieDetailItems: List<MovieDetailItem>) {
        with(binding) {
            with(viewState) {
                imgMessage.gone()
                txtMessage.gone()
                progressBar.gone()
            }
            movieDetailRecycler.visible()
            loadImage(imgPoster, movieDetail.poster)
            movieDetailAdapter.submitList(movieDetailItems)
        }
    }

    private fun showError(errorMessage: String) {
        with(binding) {
            with(viewState) {
                imgMessage.visible()
                txtMessage.apply {
                    gone()
                    text = errorMessage
                }
                progressBar.gone()
            }
            movieDetailRecycler.gone()
        }
    }

    private fun showLoading() {
        with(binding) {
            with(viewState) {
                imgMessage.gone()
                txtMessage.gone()
                progressBar.visible()
            }
            movieDetailRecycler.gone()
        }
    }
}