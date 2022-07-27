package ir.interview.idmb.data.repository.model

import ir.interview.idmb.data.database.dao.MovieDao
import ir.interview.idmb.data.network.ApiService
import ir.interview.idmb.data.network.NoConnectivityException
import ir.interview.idmb.mapper.*
import ir.interview.idmb.ui.movie.MovieDetail
import ir.interview.idmb.ui.movies.Movie
import kotlinx.coroutines.flow.flow

class MovieRepository(private val movieDao: MovieDao,private val api: ApiService) {


    suspend fun getMovies(searchText: String = "batman") = flow<DataResult<List<Movie>>> {
        try {
            emit(DataResult.Loading)
            val response = api.getMovies(searchText)
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(DataResult.Data(listMovieResponseToListMovie(it.data)))
                    movieDao.insertMovies(listMovieResponseToListMovieEntity(it.data))
                } ?: emit(DataResult.Error("data is null"))
            } else {
                emit(DataResult.Error(response.message()))
            }
        } catch (e: NoConnectivityException) {
            emit(DataResult.NoInternet)
            val localData = movieDao.getMovies(searchText)
            if (localData.isEmpty()) emit(DataResult.NoData)
            else emit(DataResult.Data(listMovieEntityToListMovie(localData)))
        } catch (e: Exception) {
            emit(DataResult.Error(e.message!!))
        }
    }

    suspend fun getMovie(movieId: String) = flow<DataResult<MovieDetail>> {
        try {
            emit(DataResult.Loading)
            val response = api.getMovie(movieId)
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(DataResult.Data(movieDetailResponseToMovieDetail(it)))
                    movieDao.insertMovieDetail(movieDetailResponseToMovieDetailEntity(it))
                } ?: emit(DataResult.Error("data is null"))
            } else {
                emit(DataResult.Error(response.message()))
            }
        } catch (e: NoConnectivityException) {
            emit(DataResult.NoInternet)
            val localData = movieDao.get(movieId)
            localData?.let {
                emit(DataResult.Data(movieDetailEntityToMovieDetail(it)))
            } ?: run {
                emit(DataResult.NoData)
            }
        } catch (e: Exception) {
            emit(DataResult.Error(e.message!!))
        }
    }

}