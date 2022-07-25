package ir.interview.idmb.data.network

import ir.interview.idmb.data.network.models.DataResponse
import ir.interview.idmb.data.network.models.MovieDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(".")
    suspend fun getMovies(@Query("s") searchText: String = "batman"): Response<DataResponse>

    @GET(".")
    suspend fun getMovie(@Query("i") movieId: String): Response<MovieDetailResponse>
}