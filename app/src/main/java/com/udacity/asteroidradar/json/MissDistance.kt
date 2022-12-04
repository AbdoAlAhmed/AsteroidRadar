package com.udacity.asteroidradar.json


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MissDistance(
    @Json(name = "astronomical")
    var astronomical: String?,
    @Json(name = "kilometers")
    var kilometers: String?,
    @Json(name = "lunar")
    var lunar: String?,
    @Json(name = "miles")
    var miles: String?
)