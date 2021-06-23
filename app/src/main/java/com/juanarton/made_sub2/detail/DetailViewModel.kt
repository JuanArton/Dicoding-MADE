package com.juanarton.made_sub2.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.juanarton.core.domain.DataParcel
import com.juanarton.core.domain.RepositoryUseCase

class DetailViewModel(private val repositoryUseCase: RepositoryUseCase): ViewModel() {
    fun getDetailMovieByID(movieID: String) = repositoryUseCase.getDetailMovieById(movieID).asLiveData()
    fun insertToFav(favorite: DataParcel){
        repositoryUseCase.insertToFav(favorite)
    }
    fun deleteFromFav(favorite: DataParcel){
        repositoryUseCase.deleteFromFav(favorite)
    }

    fun getFavDetail(id: String) = repositoryUseCase.getFavDetail(id).asLiveData()
}