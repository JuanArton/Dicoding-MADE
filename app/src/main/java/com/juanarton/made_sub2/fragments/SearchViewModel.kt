package com.juanarton.made_sub2.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.juanarton.core.domain.RepositoryUseCase

class SearchViewModel(repositoryUseCase: RepositoryUseCase): ViewModel() {
    val listMovie = repositoryUseCase.getPopularList().asLiveData()
}