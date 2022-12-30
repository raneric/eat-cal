package com.sgg.eatcal.service

import com.sgg.eatcal.BuildConfig
import com.sgg.eatcal.model.Recipe
import com.sgg.eatcal.model.Results
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private val BASE_URL = BuildConfig.SPOONACULAR_BASE_URL

private val moshiConverter = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

private val logging: HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(
    HttpLoggingInterceptor.Level.BASIC)

private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()
private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshiConverter))
        .baseUrl(BASE_URL)
        .client(client)
        .build()

interface SpoonacularService {
    @GET("complexSearch?apiKey=${BuildConfig.SPOONACULAR_API_KEY}")
    suspend fun testRequst(): Results
}

object SpoonacularApi {
    val spoonacularService: SpoonacularService by lazy { retrofit.create(SpoonacularService::class.java) }
}