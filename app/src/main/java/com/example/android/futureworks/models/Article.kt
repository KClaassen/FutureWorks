package com.example.android.futureworks.models

import com.squareup.moshi.Json

data class Article(
    val date: String,
    val id: Int,
    val summary: String,
    @Json(name = "thumbnail_url") val thumbnailUrl: String,
    val title: String
)

