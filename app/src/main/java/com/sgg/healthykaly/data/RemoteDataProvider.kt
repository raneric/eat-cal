package com.sgg.healthykaly.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sgg.healthykaly.model.RecipeModel
import com.sgg.healthykaly.service.RecipeService
import com.sgg.healthykaly.utils.QueryConstants
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteDataProvider @Inject constructor(private val recipeService: RecipeService) :
        RecipeDataSourceProvider {
    override fun getFlowOfRecipes(queries: Map<String, Int>): Flow<PagingData<RecipeModel>> {
        return Pager(config = PagingConfig(pageSize = QueryConstants.DEFAULT_LOAD_SIZE,
                                           enablePlaceholders = false),
                     pagingSourceFactory = { RecipePagingSource(recipeService, queries) }).flow
    }
}