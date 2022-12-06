package com.udacity.asteroidradar.data.sql

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.api.endDataFormatted

@Entity(tableName = "pod_entity")
data class PodEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Long = 0L,
    @Json(name = "media_type") val mediaType: String
    ,val title: String
    ,val url: String
    ,val day: String = endDataFormatted()

)

fun PodEntity.asPodModel():PictureOfDay{
    return PictureOfDay(
        url = url,
        mediaType = mediaType,
        title = title
    )
}

