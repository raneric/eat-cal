package com.sgg.healthykaly.repository

import androidx.paging.PagingData
import com.sgg.healthykaly.data.RecipeDatabase
import com.sgg.healthykaly.model.RecipeModel
import kotlinx.coroutines.flow.Flow

class DatabaseProvider(private val remoteDataProvider: RemoteDataProvider,
                       private val database: RecipeDatabase) :
        RecipeDataSourceProvider {
    override fun getFlowOfRecipes(queries: Map<String, Int>): Flow<PagingData<RecipeModel>> {
        TODO("Not yet implemented")
    }
}