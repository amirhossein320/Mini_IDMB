package ir.interview.idmb.mapper

import ir.interview.idmb.data.database.entities.MovieDetailEntity
import ir.interview.idmb.data.network.models.MovieDetailResponse
import ir.interview.idmb.ui.movie.MovieDetail

suspend inline fun movieDetailResponseToMovieDetailEntity(input: MovieDetailResponse): MovieDetailEntity =
    MovieDetailEntity(
        title = input.title,
        year = input.year,
        rated = input.rated,
        released = input.released,
        runtime = input.runtime,
        genre = input.genre,
        director = input.director,
        writer = input.writer,
        actors = input.actors,
        plot = input.plot,
        language = input.language,
        country = input.country,
        awards = input.awards,
        poster = input.poster,
        metascore = input.metascore,
        imdbRating = input.imdbRating,
        imdbVotes = input.imdbVotes,
        imdbID = input.imdbID,
        type = input.type,
        dvd = input.dvd,
        boxOffice = input.boxOffice,
        production = input.production,
        website = input.website,
    )

suspend inline fun movieDetailResponseToMovieDetail(input: MovieDetailResponse): MovieDetail =
    MovieDetail(
        title = input.title,
        year = input.year,
        rated = input.rated,
        released = input.released,
        runtime = input.runtime,
        genre = input.genre,
        director = input.director,
        writer = input.writer,
        actors = input.actors,
        plot = input.plot,
        language = input.language,
        country = input.country,
        awards = input.awards,
        poster = input.poster,
        metaScore = input.metascore,
        imdbRating = input.imdbRating,
        imdbVotes = input.imdbVotes,
        imdbID = input.imdbID,
        type = input.type,
        dvd = input.dvd,
        boxOffice = input.boxOffice,
        production = input.production,
        website = input.website,
    )


suspend inline fun movieDetailEntityToMovieDetail(input: MovieDetailEntity): MovieDetail =
    MovieDetail(
        title = input.title,
        year = input.year,
        rated = input.rated,
        released = input.released,
        runtime = input.runtime,
        genre = input.genre,
        director = input.director,
        writer = input.writer,
        actors = input.actors,
        plot = input.plot,
        language = input.language,
        country = input.country,
        awards = input.awards,
        poster = input.poster,
        metaScore = input.metascore,
        imdbRating = input.imdbRating,
        imdbVotes = input.imdbVotes,
        imdbID = input.imdbID,
        type = input.type,
        dvd = input.dvd,
        boxOffice = input.boxOffice,
        production = input.production,
        website = input.website,
    )
