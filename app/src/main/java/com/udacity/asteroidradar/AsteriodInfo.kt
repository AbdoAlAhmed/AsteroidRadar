package com.udacity.asteroidradar

data class AsteriodInfo(
    val id: Int,
    val absolute_magnitude: String,
    val estimated_diameter_max: String,
    val is_potentially_hazardous_asteroid: Boolean,
    val close_approach_data : String
)
