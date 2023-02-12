package com.sgg.healthykaly.service

import com.sgg.healthykaly.BuildConfig
import com.sgg.healthykaly.model.Recipe
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = BuildConfig.BASE_URL

private val moshiConverter = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

/**
 * Retrofit request logger interceptor at basic logging level
 */
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

/**
 * Singleton that hold recipe service created by lazy
 */
object RecipeApi {
    val recipeService: RecipeService by lazy { retrofit.create(RecipeService::class.java) }
}