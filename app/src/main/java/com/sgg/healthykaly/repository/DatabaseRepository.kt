package com.sgg.healthykaly.repository

import com.sgg.healthykaly.model.Recipe
import com.sgg.healthykaly.utils.QueryBuilder

class DatabaseRepository : RecipeRepository {
    override suspend fun getAllRecipe(): List<Recipe> {
        TODO("Not yet implemented")
    }
}