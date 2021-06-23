package com.juanarton.core.repo.local.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface DBDao {

    @Query("SELECT * FROM movie")
    fun getMovie(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = MovieEntity::class)
    fun insertMovie(movie: List<MovieEntity>)

    @Query("SELECT * FROM movie WHERE id = :movieId")
    fun getDetailMovieById(movieId: String): Flow<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = FavoriteEntity::class)
    fun insertToFav(favorite: FavoriteEntity)

    @Delete
    fun deleteFromFav(favorite: FavoriteEntity)

    @Query("SELECT * FROM favorite")
    fun getListFavorite(): Flow<List<FavoriteEntity>>

    @Query("SELECT * FROM favorite WHERE id = :id")
    fun getFavDetail(id: String): Flow<FavoriteEntity>
}