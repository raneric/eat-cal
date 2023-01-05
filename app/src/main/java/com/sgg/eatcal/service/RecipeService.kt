package com.sgg.eatcal.service

import com.sgg.eatcal.BuildConfig
import com.sgg.eatcal.model.Recipe
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

interface RecipeService {
    @GET("findByNutrients?apiKey=${BuildConfig.API_KEY}")
    suspend fun findByFat(@Query(value = "maxFat") maxFat: Int): List<Recipe>
}

object RecipeApi {
    val recipeService: RecipeService by lazy { retrofit.create(RecipeService::class.java) }
}