package ir.interview.idmb.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ir.interview.idmb.data.database.dao.MovieDao
import ir.interview.idmb.data.database.entities.FullMovieEntity
import ir.interview.idmb.data.database.entities.MovieEntity

@Database(entities = [MovieEntity::class, FullMovieEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
}