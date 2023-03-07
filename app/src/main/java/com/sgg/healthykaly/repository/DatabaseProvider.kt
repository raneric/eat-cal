package com.sgg.healthykaly.repository

import androidx.paging.PagingData
import com.sgg.healthykaly.model.Recipe
import kotlinx.coroutines.flow.Flow

class DatabaseProvider(private val remoteDataProvider: RemoteDataProvider) :
        RecipeDataSourceProvider {
    override fun getFlowOfRecipes(queries: Map<String, Int>): Flow<PagingData<Recipe>> {
        TODO("Not yet implemented")
    }
}