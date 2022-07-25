package ir.interview.idmb.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import ir.interview.idmb.data.database.entities.FullMovieEntity
import ir.interview.idmb.data.database.entities.MovieEntity
import ir.interview.idmb.data.network.models.FullMovieResponse

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    suspend fun getAll(): List<MovieEntity>

    @Query("SELECT * FROM full_movies WHERE imdbID=:movieId")
    suspend fun get(movieId: String): FullMovieEntity

    @Insert(onConflict = REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Insert(onConflict = REPLACE)
    suspend fun insertMovie(movie: FullMovieResponse)
}