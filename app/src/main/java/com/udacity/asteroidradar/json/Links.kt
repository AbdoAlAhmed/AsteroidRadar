package com.udacity.asteroidradar.json


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Links(
    @Json(name = "next")
    var next: String?,
    @Json(name = "previous")
    var previous: String?,
    @Json(name = "self")
    var self: String?
)