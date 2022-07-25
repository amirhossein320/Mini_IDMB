package ir.interview.idmb.mapper

import ir.interview.idmb.data.database.entities.MovieEntity
import ir.interview.idmb.data.network.models.MovieResponse
import ir.interview.idmb.ui.movies.Movie

suspend inline fun movieResponseToMovieEntity(input: MovieResponse): MovieEntity =
    MovieEntity(
        imdbID = input.imdbID,
        title = input.title,
        year = input.year,
        type = input.type,
        poster = input.poster,
    )

suspend inline fun listMovieResponseToListMovieEntity(input: List<MovieResponse>): List<MovieEntity> {
    val output = mutableListOf<MovieEntity>()
    input.forEach { output.add(movieResponseToMovieEntity(it)) }
    return output
}


suspend inline fun movieResponseToMovie(input: MovieResponse): Movie =
    Movie(
        imdbID = input.imdbID,
        title = input.title,
        year = input.year,
        type = input.type,
        poster = input.poster
    )

suspend inline fun listMovieResponseToListMovie(input: List<MovieResponse>): List<Movie> {
    val output = mutableListOf<Movie>()
    input.forEach { output.add(movieResponseToMovie(it)) }
    return output
}


suspend inline fun movieEntityToMovie(input: MovieEntity): Movie =
    Movie(
        imdbID = input.imdbID,
        title = input.title,
        year = input.year,
        type = input.type,
        poster = input.poster
    )

suspend inline fun listMovieEntityToListMovie(input: List<MovieEntity>): List<Movie> {
    val output = mutableListOf<Movie>()
    input.forEach { output.add(movieEntityToMovie(it)) }
    return output
}



