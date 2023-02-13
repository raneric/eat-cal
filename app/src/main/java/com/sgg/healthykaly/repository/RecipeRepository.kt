package com.sgg.healthykaly.repository

import com.sgg.healthykaly.model.Recipe

interface RecipeRepository {
    suspend fun getAllRecipe(): List<Recipe>
}