package com.udacity.asteroidradar.json


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CloseApproachData(
    @Json(name = "close_approach_date")
    var closeApproachDate: String?,
    @Json(name = "close_approach_date_full")
    var closeApproachDateFull: String?,
    @Json(name = "epoch_date_close_approach")
    var epochDateCloseApproach: Long?,
    @Json(name = "miss_distance")
    var missDistance: MissDistance?,
    @Json(name = "orbiting_body")
    var orbitingBody: String?,
    @Json(name = "relative_velocity")
    var relativeVelocity: RelativeVelocity?
)