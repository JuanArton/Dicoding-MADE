package com.juanarton.favorite.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.juanarton.core.domain.RepositoryUseCase

class FavoriteViewModel(repositoryUseCase: RepositoryUseCase): ViewModel() {
    val listFavorite = repositoryUseCase.getFavList().asLiveData()
}