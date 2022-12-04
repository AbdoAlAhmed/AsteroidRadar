package com.udacity.asteroidradar.json


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CloseApproachDataX(
    @Json(name = "close_approach_date")
    var closeApproachDate: String?,
    @Json(name = "close_approach_date_full")
    var closeApproachDateFull: String?,
    @Json(name = "epoch_date_close_approach")
    var epochDateCloseApproach: Long?,
    @Json(name = "orbiting_body")
    var orbitingBody: String?

)