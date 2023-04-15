package com.sgg.healthykaly.data

import androidx.paging.PagingData
import com.sgg.healthykaly.di.RemoteSource
import com.sgg.healthykaly.model.RecipeModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DatabaseProvider @Inject constructor(@RemoteSource private val remoteDataProvider: RecipeDataSourceProvider) :
        RecipeDataSourceProvider {
    override fun getFlowOfRecipes(queries: Map<String, Int>): Flow<PagingData<RecipeModel>> {
        TODO("Not yet implemented")
    }
}