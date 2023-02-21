package com.sgg.healthykaly.repository

import android.util.Log
import com.sgg.healthykaly.model.Recipe
import com.sgg.healthykaly.service.RecipeService
import com.sgg.healthykaly.utils.QueryBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NetworkRecipeRepository(private val recipeService: RecipeService) : RecipeRepository {

    override suspend fun getAllRecipe(): List<Recipe> {
        var recipes: List<Recipe> = emptyList()
        withContext(Dispatchers.IO) {
            try {
                recipes = recipeService.findByNutriments()
            } catch (e: Exception) {
                Log.e(TAG_RETROFIT_ERROR, e.toString())
            }
        }
        return recipes
    }
}