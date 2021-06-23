package com.juanarton.core.repo.local.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
data class FavoriteEntity (
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "release_date")
    val releaseYear: String,

    @ColumnInfo(name = "imglink")
    val imageLink: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "backdrop")
    val backdrop: String
)