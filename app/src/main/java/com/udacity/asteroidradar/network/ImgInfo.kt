package com.udacity.asteroidradar.network

import android.widget.ImageView
import retrofit2.http.Url

data class ImgInfo(
    val url: String,
    val media_type: ImageView,
    val title: String
)