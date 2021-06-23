package com.juanarton.core.api

import retrofit2.http.GET
import retrofit2.http.Query


interface APIService {
    @GET("3/movie/popular")
    suspend fun getPopular(
        @Query("api_key") key: String,
        @Query("language") username: String,
        @Query("page") page: Int
    ): Response
}