package com.sgg.healthykaly.service

import com.sgg.healthykaly.BuildConfig
import com.sgg.healthykaly.model.Recipe
import com.sgg.healthykaly.utils.QueryBuilder
import retrofit2.http.GET
import retrofit2.http.QueryMap

private const val API_KEY = BuildConfig.API_KEY

interface RecipeService {
    @GET("findByNutrients?apiKey=${API_KEY}")
    suspend fun findByNutriments(@QueryMap queries: Map<String, Int> = QueryBuilder.defaultQuery): List<Recipe>

}