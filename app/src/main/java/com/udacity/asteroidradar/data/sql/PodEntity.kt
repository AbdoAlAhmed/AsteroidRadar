package com.udacity.asteroidradar.data.sql

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "pod_entity")
data class PodEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Long = 0L,
    @Json(name = "media_type") val mediaType: String
    ,val title: String
    ,val url: String
)