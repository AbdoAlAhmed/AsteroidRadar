package com.udacity.asteroidradar.json


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NearEarthObjects(
    @Json(name = "2015-09-07")
    var x20150907: List<X20150907>?,
    @Json(name = "2015-09-08")
    var x20150908: List<X20150908>?
)