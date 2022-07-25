package ir.interview.idmb.mapper

import ir.interview.idmb.data.database.entities.FullMovieEntity
import ir.interview.idmb.data.database.entities.MovieEntity
import ir.interview.idmb.data.network.models.FullMovieResponse
import ir.interview.idmb.data.network.models.MovieResponse
import ir.interview.idmb.ui.movie.FullMovie
import ir.interview.idmb.ui.movies.Movie

suspend inline fun fullMovieResponseToFullMovieEntity(input: FullMovieResponse): FullMovieEntity =
    FullMovieEntity(
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

suspend inline fun fullMovieResponseToFullMovie(input: FullMovieResponse): FullMovie =
    FullMovie(
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


suspend inline fun fullMovieEntityToFullMovie(input: FullMovieEntity): FullMovie =
    FullMovie(
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
