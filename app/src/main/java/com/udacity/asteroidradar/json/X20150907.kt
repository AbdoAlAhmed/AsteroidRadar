package com.udacity.asteroidradar.json


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class X20150907(
    @Json(name = "absolute_magnitude_h")
    var absoluteMagnitudeH: Double?,
    @Json(name = "close_approach_data")
    var closeApproachData: List<CloseApproachData>?,
    @Json(name = "estimated_diameter")
    var estimatedDiameter: EstimatedDiameter?,
    @Json(name = "id")
    var id: String?,
    @Json(name = "is_potentially_hazardous_asteroid")
    var isPotentiallyHazardousAsteroid: Boolean?,
    @Json(name = "is_sentry_object")
    var isSentryObject: Boolean?,
    @Json(name = "links")
    var links: LinksX?,
    @Json(name = "name")
    var name: String?,
    @Json(name = "nasa_jpl_url")
    var nasaJplUrl: String?,
    @Json(name = "neo_reference_id")
    var neoReferenceId: String?
)