package com.sgg.healthykaly.repository

import androidx.paging.PagingData
import com.sgg.healthykaly.model.Recipe
import com.sgg.healthykaly.utils.QueryBuilder
import kotlinx.coroutines.flow.Flow


class RecipeRepository(private val recipeDataSource: RecipeDataSourceProvider) {
    fun getRecipes(query: Map<String, Int> = QueryBuilder.defaultQuery): Flow<PagingData<Recipe>> {
        return recipeDataSource.getFlowOfRecipes(query)
    }
}