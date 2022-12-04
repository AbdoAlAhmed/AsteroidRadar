package com.udacity.asteroidradar.json


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AsteroidInfo2(
    @Json(name = "element_count")
    var elementCount: Int?,
    @Json(name = "links")
    var links: Links?,
    @Json(name = "near_earth_objects")
    var nearEarthObjects: NearEarthObjects?
)