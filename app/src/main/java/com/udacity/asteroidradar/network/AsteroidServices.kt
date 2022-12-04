package com.udacity.asteroidradar.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.json.AsteroidInfo2
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private fun httpLogging(): HttpLoggingInterceptor {
    val logger = HttpLoggingInterceptor()
    logger.level = HttpLoggingInterceptor.Level.BODY
    return logger
}

private fun client(): OkHttpClient {
    return OkHttpClient()
        .newBuilder()
        .addInterceptor(httpLogging())
        .addInterceptor(AsteroidUrl())
        .build()
}

private val retrofit = Retrofit.Builder()
    .baseUrl(Constants.BASE_URL)
    .client(client())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .build()


interface AsteroidServices {

    @GET("neo/rest/v1/feed")
    suspend fun getData(
        @Query("start_date") startData: String,
        @Query("end_date") endData: String,
        @Query("api_key") apiKey: String
    ): Response<AsteroidInfo2>

    @GET("planetary/apod")
    suspend fun getImage(@Query("api_key") apiKey: String): List<ImgInfo>


}

object AsteroidApi {
    val asteroidServicesApi: AsteroidServices = retrofit.create(AsteroidServices::class.java)
}

class AsteroidUrl : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val chainRequest = chain.request()
        val httpUrl = chainRequest.url
        val url = httpUrl.newBuilder().addQueryParameter("api_key", Constants.API_KEY).build()
        val request = chainRequest.newBuilder().url(url).build()
        return chain.proceed(request)
    }

}