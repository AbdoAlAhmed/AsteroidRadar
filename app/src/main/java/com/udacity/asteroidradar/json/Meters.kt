package com.udacity.asteroidradar.json


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Meters(
    @Json(name = "estimated_diameter_max")
    var estimatedDiameterMax: Double?,
    @Json(name = "estimated_diameter_min")
    var estimatedDiameterMin: Double?
)