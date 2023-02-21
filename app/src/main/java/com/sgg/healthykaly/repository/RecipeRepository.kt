package com.sgg.healthykaly.repository

import com.sgg.healthykaly.model.Recipe
import com.sgg.healthykaly.utils.QueryBuilder

const val TAG_RETROFIT_ERROR = "Retrofit Error"
interface RecipeRepository {
    suspend fun getAllRecipe(): List<Recipe>
}