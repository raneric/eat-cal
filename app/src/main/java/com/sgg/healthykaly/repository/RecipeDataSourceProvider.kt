package com.sgg.healthykaly.repository

import androidx.paging.PagingData
import com.sgg.healthykaly.model.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeDataSourceProvider {
    fun getFlowOfRecipes(queries: Map<String, Int>): Flow<PagingData<Recipe>>
}