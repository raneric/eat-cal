package com.sgg.healthykaly.service

import com.sgg.healthykaly.BuildConfig
import com.sgg.healthykaly.model.RecipeModel
import com.sgg.healthykaly.model.RecipeSummaryModel
import com.sgg.healthykaly.utils.QueryBuilder
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

private const val API_KEY = BuildConfig.API_KEY

interface RecipeService {

    /**
     * Fetching recipes list by nutriment from spoonacular API
     * @param queries: type Map<String, Int> it will be the request param to the API
     * @return List<RecipeModel> all recipes matching all queries
     */
    @GET("findByNutrients?apiKey=${API_KEY}")
    suspend fun findByNutriments(@QueryMap queries: Map<String, Int> = QueryBuilder.defaultQuery): List<RecipeModel>

    @GET("{id}/summary?apiKey=${API_KEY}")
    suspend fun findRecipeSummary(@Path("id") id: Int): RecipeSummaryModel
}