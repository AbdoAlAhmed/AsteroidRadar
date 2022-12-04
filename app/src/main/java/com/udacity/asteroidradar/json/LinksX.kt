package com.udacity.asteroidradar.json


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LinksX(
    @Json(name = "self")
    var self: String?
)