package com.sgg.healthykaly.repository

import androidx.paging.PagingData
import com.sgg.healthykaly.model.RecipeModel
import kotlinx.coroutines.flow.Flow

interface RecipeDataSourceProvider {
    fun getFlowOfRecipes(queries: Map<String, Int>): Flow<PagingData<RecipeModel>>
}