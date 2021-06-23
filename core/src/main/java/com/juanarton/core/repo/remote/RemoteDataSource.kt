package com.juanarton.core.repo.remote

import com.juanarton.core.BuildConfig
import com.juanarton.core.api.API
import com.juanarton.core.api.MovieResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import java.lang.Exception

class RemoteDataSource {
    suspend fun getPopularList(): Flow<MovieResponse> {
        return try {
            API.services.getPopular(BuildConfig.API_KEY, "en-US", 1).movieList.asFlow()
        } catch (e: Exception) {
            ArrayList<MovieResponse>().asFlow()
        }
    }
}