package com.juanarton.core.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataParcel (
    val id: String,
    val title: String,
    val releaseYear: String,
    val imageLink: String,
    val description: String,
    val backdrop: String
) : Parcelable