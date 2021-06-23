package com.juanarton.core.dataConverter

import com.juanarton.core.api.MovieResponse
import com.juanarton.core.domain.DataParcel
import com.juanarton.core.repo.local.room.FavoriteEntity
import com.juanarton.core.repo.local.room.MovieEntity

object DataConverter {
    fun movieResponseToMovieEntity(listMovie: List<MovieResponse>): List<MovieEntity>{
        val listData = ArrayList<MovieEntity>()
        for (element in listMovie) {
            try {
                val data = MovieEntity(
                    element.id,
                    element.title,
                    element.release_date,
                    element.address,
                    element.description,
                    element.backdrop
                )
                listData.add(data)
            }catch (e: Exception){
                val data = MovieEntity(
                    "Server return null",
                    "Server return null",
                    "Server return null",
                    "Server return null",
                    "Server return null",
                    "Server return null"
                )
                listData.add(data)
            }
        }
        return listData
    }

    fun movieEntityToDataParcel(listFromDB: List<MovieEntity>): List<DataParcel>{
        val listData = ArrayList<DataParcel>()
        for (element in listFromDB) {
            val data = DataParcel(
                element.id,
                element.title,
                element.releaseYear,
                element.imageLink,
                element.description,
                element.backdrop
            )
            listData.add(data)
        }
        return listData
    }

    fun favEntityToDataParcel(listFromDB: List<FavoriteEntity>): List<DataParcel>{
        val listData = ArrayList<DataParcel>()
        if(listFromDB.isNotEmpty()){
            for (element in listFromDB) {
                val data = DataParcel(
                    element.id,
                    element.title,
                    element.releaseYear,
                    element.imageLink,
                    element.description,
                    element.backdrop
                )
                listData.add(data)
            }
        }
        return listData
    }

    fun MovieEntity.movieDetailToDataParcel() = DataParcel(
        id = id,
        title = title,
        releaseYear = releaseYear,
        imageLink = imageLink,
        description = description,
        backdrop = backdrop
    )

    fun DataParcel.dataParcelToFavEntity() = FavoriteEntity(
        id = id,
        title = title,
        releaseYear = releaseYear,
        imageLink = imageLink,
        description = description,
        backdrop = backdrop
    )

    fun FavoriteEntity.favEntityToSingleDataParcel() = DataParcel(
        id = id,
        title = title,
        releaseYear = releaseYear,
        imageLink = imageLink,
        description = description,
        backdrop = backdrop
    )
}