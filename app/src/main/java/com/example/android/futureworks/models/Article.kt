package com.example.android.futureworks.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Article(
    val date: String,
    @PrimaryKey
    val id: Int,
    val summary: String,
    @Json(name = "thumbnail_url") val thumbnailUrl: String,
    val title: String
) : Parcelable

