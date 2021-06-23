package com.juanarton.favorite.di

import com.juanarton.favorite.fragments.FavoriteViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteMovieModule = module {
    viewModel { FavoriteViewModel(get()) }
}