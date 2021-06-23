package com.juanarton.core.domain

class RepositoryInteractor(private val movieRepository: RepositoryInterface): RepositoryUseCase {
    override fun getPopularList() = movieRepository.getPopularList()
    override fun getDetailMovieById(movieId: String) = movieRepository.getDetailMovieById(movieId)
    override fun insertToFav(favorite: DataParcel) {
        movieRepository.insertToFav(favorite)
    }

    override fun deleteFromFav(favorite: DataParcel) {
        movieRepository.deleteFromFav(favorite)
    }

    override fun getFavList() = movieRepository.getFavList()
    override fun getFavDetail(id: String) = movieRepository.getFavDetail(id)
}