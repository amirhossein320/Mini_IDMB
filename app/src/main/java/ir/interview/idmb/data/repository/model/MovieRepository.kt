package ir.interview.idmb.data.repository.model

import ir.interview.idmb.data.database.dao.MovieDao
import ir.interview.idmb.data.network.ApiService
import ir.interview.idmb.data.network.NoConnectivityException
import ir.interview.idmb.ui.movies.Movie
import kotlinx.coroutines.flow.flow

class MovieRepository(private val movieDao: MovieDao, api: ApiService) {


    suspend fun getMovies(searchText: String = "s") = flow<DataResult<List<Movie>>> {
        try {

        } catch (e: NoConnectivityException) {
            emit(DataResult.NoInternet)
            movieDao.getAll()

        } catch (e: Exception) {
            emit(DataResult.Error(e.message!!))
        }
    }
}