package ir.interview.idmb.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import ir.interview.idmb.data.database.entities.MovieDetailEntity
import ir.interview.idmb.data.database.entities.MovieEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    suspend fun getAll(): List<MovieEntity>

    @Query("SELECT * FROM movie_details WHERE imdbID=:movieId")
    suspend fun get(movieId: String): MovieDetailEntity?

    @Insert(onConflict = REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Insert(onConflict = REPLACE)
    suspend fun insertMovieDetail(movie: MovieDetailEntity)
}