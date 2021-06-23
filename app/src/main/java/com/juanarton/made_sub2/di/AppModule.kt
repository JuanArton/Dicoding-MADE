package com.juanarton.made_sub2.di

import com.juanarton.core.domain.RepositoryInteractor
import com.juanarton.core.domain.RepositoryUseCase
import com.juanarton.made_sub2.detail.DetailViewModel
import com.juanarton.made_sub2.fragments.HomeViewModel
import com.juanarton.made_sub2.fragments.SearchViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<RepositoryUseCase> { RepositoryInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { SearchViewModel(get()) }
}