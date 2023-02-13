package com.sgg.healthykaly.repository

import android.util.Log
import com.sgg.healthykaly.model.Recipe
import com.sgg.healthykaly.service.RecipeService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NetworkRecipeRepository(private val recipeService: RecipeService) : RecipeRepository {

    override suspend fun getAllRecipe(): List<Recipe> {
        var recipes: List<Recipe> = emptyList()
        withContext(Dispatchers.IO) {
            try {
                recipes = recipeService.findByFat(DEFAULT_FAT)
            } catch (e: Exception) {
                Log.e("Retrofit Error", e.toString())
            }
        }
        return recipes
    }

    companion object {
        const val DEFAULT_FAT = 25
    }
}