package com.udacity.asteroidradar.data

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.PictureOfDay
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val interceptor: HttpLoggingInterceptor =
    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
private val client = OkHttpClient().newBuilder().addInterceptor(interceptor).build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(Constants.BASE_URL)
    .client(client)
    .build()


interface AsteroidServices {
    @GET("neo/rest/v1/feed")
    suspend fun getAsteroids(
        @Query("start_date") startData : String,
        @Query("end_date") endData : String,
        @Query("api_key") apiKey : String
    ):Response<String>

   @GET("planetary/apod")
   suspend fun getPicture(@Query("api_key") apiKey : String) : Response<PictureOfDay>

}

object AsteroidApi {
    val asteroidServicesApi: AsteroidServices = retrofit.create(AsteroidServices::class.java)
}
