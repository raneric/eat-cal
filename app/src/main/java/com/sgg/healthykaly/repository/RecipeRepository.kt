package com.sgg.healthykaly.repository

import androidx.paging.PagingData
import com.sgg.healthykaly.di.RemoteSource
import com.sgg.healthykaly.model.RecipeModel
import com.sgg.healthykaly.utils.QueryBuilder
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipeRepository @Inject constructor(
        @RemoteSource
        private val recipeDataSource: RecipeDataSourceProvider
) {
    fun getRecipes(query: Map<String, Int> = QueryBuilder.defaultQuery): Flow<PagingData<RecipeModel>> {
        return recipeDataSource.getFlowOfRecipes(query)
    }
}