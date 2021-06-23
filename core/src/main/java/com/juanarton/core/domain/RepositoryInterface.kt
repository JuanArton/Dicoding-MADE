package com.juanarton.core.domain

import kotlinx.coroutines.flow.Flow

interface RepositoryInterface{
    fun getPopularList(): Flow<List<DataParcel>>
    fun getDetailMovieById(movieId: String): Flow<DataParcel>
    fun insertToFav(favorite: DataParcel)
    fun deleteFromFav(favorite: DataParcel)
    fun getFavDetail(id: String) : Flow<DataParcel>
    fun getFavList(): Flow<List<DataParcel>>
}