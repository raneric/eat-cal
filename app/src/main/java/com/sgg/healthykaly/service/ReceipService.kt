package com.sgg.healthykaly.service

import com.sgg.healthykaly.BuildConfig
import com.sgg.healthykaly.model.Recipe
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = BuildConfig.API_KEY

interface RecipeService {
    @GET("findByNutrients?apiKey=${API_KEY}")
    suspend fun findByFat(@Query(value = "maxFat") maxFat: Int): List<Recipe>
}