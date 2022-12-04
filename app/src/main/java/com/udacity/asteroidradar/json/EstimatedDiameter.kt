package com.udacity.asteroidradar.json


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EstimatedDiameter(
    @Json(name = "feet")
    var feet: Feet?,
    @Json(name = "kilometers")
    var kilometers: Kilometers?,
    @Json(name = "meters")
    var meters: Meters?,
    @Json(name = "miles")
    var miles: Miles?
)