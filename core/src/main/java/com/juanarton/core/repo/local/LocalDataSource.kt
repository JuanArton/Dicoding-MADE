package com.juanarton.core.repo.local

import com.juanarton.core.repo.local.room.DBDao
import com.juanarton.core.repo.local.room.FavoriteEntity
import com.juanarton.core.repo.local.room.MovieEntity
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val dbDao: DBDao) {

    fun getMovie(): Flow<List<MovieEntity>> = dbDao.getMovie()

    fun insertMovie(movie: List<MovieEntity>) = dbDao.insertMovie(movie)

    fun getDetailMovieById(movieId: String): Flow<MovieEntity> = dbDao.getDetailMovieById(movieId)

    fun insertToFav(favorite: FavoriteEntity) = dbDao.insertToFav(favorite)

    fun deleteFromFav(favorite: FavoriteEntity) = dbDao.deleteFromFav(favorite)

    fun getListFavorite(): Flow<List<FavoriteEntity>> = dbDao.getListFavorite()

    fun getFavDetail(id: String) : Flow<FavoriteEntity> = dbDao.getFavDetail(id)
}