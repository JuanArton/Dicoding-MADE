package com.juanarton.core.repo

import android.util.Log
import com.juanarton.core.dataConverter.DataConverter
import com.juanarton.core.dataConverter.DataConverter.dataParcelToFavEntity
import com.juanarton.core.dataConverter.DataConverter.favEntityToSingleDataParcel
import com.juanarton.core.dataConverter.DataConverter.movieDetailToDataParcel
import com.juanarton.core.domain.DataParcel
import com.juanarton.core.domain.RepositoryInterface
import com.juanarton.core.repo.local.LocalDataSource
import com.juanarton.core.repo.remote.RemoteDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): RepositoryInterface {

    @FlowPreview
    override fun getPopularList(): Flow<List<DataParcel>> {
        return flow{
            val listMovie = remoteDataSource.getPopularList().toList()
            Log.d("movdata", listMovie.toString())
            localDataSource.insertMovie(DataConverter.movieResponseToMovieEntity(listMovie))

            localDataSource.getMovie().collect{ states ->
                if (states.isEmpty()) {
                    emit(ArrayList<DataParcel>())
                } else {
                    emit(DataConverter.movieEntityToDataParcel(states))
                }
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun getDetailMovieById(movieId: String): Flow<DataParcel> =
        localDataSource.getDetailMovieById(movieId).map {
            it.movieDetailToDataParcel()
        }

    override fun insertToFav(favorite: DataParcel) {
        CoroutineScope(Dispatchers.IO).launch {
            localDataSource.insertToFav(favorite.dataParcelToFavEntity())
        }
    }

    override fun deleteFromFav(favorite: DataParcel) {
        CoroutineScope(Dispatchers.IO).launch {
            localDataSource.deleteFromFav(favorite.dataParcelToFavEntity())
        }
    }

    override fun getFavDetail(id: String): Flow<DataParcel> {
        return localDataSource.getFavDetail(id).filterNotNull().map{
            it.favEntityToSingleDataParcel()
        }
    }

    override fun getFavList(): Flow<List<DataParcel>> {
        return localDataSource.getListFavorite().map{
            DataConverter.favEntityToDataParcel(it)
        }
    }
}