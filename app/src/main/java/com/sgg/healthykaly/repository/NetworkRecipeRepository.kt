package com.sgg.healthykaly.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sgg.healthykaly.data.RecipePagingSource
import com.sgg.healthykaly.model.Recipe
import com.sgg.healthykaly.service.RecipeService
import com.sgg.healthykaly.utils.QueryBuilder
import com.sgg.healthykaly.utils.QueryConstants.DEFAULT_LOAD_SIZE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class NetworkRecipeRepository(private val recipeService: RecipeService) {

    fun getFlowOfRecipe(queries: Map<String, Int>): Flow<PagingData<Recipe>> {
        return Pager(config = PagingConfig(pageSize = DEFAULT_LOAD_SIZE,
                                           enablePlaceholders = false),
                     pagingSourceFactory = { RecipePagingSource(recipeService, queries) }).flow

    }
}