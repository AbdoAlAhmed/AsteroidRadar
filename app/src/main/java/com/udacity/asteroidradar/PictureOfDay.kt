package com.udacity.asteroidradar

import com.squareup.moshi.Json
import com.udacity.asteroidradar.data.sql.PodEntity

data class PictureOfDay(
    @Json(name = "media_type") val mediaType: String, val title: String,
    val url: String
)

fun PictureOfDay.asPodEntity():PodEntity{
    return PodEntity(
        url = url,
        mediaType = mediaType,
        title = title
    )
}