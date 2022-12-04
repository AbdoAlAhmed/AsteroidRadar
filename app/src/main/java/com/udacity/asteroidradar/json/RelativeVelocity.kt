package com.udacity.asteroidradar.json


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RelativeVelocity(
    @Json(name = "kilometers_per_hour")
    var kilometersPerHour: String?,
    @Json(name = "kilometers_per_second")
    var kilometersPerSecond: String?,
    @Json(name = "miles_per_hour")
    var milesPerHour: String?
)