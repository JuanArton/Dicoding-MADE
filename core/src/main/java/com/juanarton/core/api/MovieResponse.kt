package com.juanarton.core.api

import com.google.gson.annotations.SerializedName

data class MovieResponse (
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("release_date")
    val release_date: String,

    @field:SerializedName("poster_path")
    val address: String,

    @field:SerializedName("overview")
    val description: String,

    @field:SerializedName("backdrop_path")
    val backdrop: String
)