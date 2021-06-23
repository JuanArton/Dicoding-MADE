package com.juanarton.core.api

import com.google.gson.annotations.SerializedName

data class Response (
    @field:SerializedName("results")
    val movieList: List<MovieResponse>
)