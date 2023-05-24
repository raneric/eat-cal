package com.sgg.healthykaly.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sgg.healthykaly.data.RecipeDatabase
import com.sgg.healthykaly.data.RecipeRemoteMediator
import com.sgg.healthykaly.model.RecipeEntity
import com.sgg.healthykaly.model.RecipeSummaryModel
import com.sgg.healthykaly.model.SummaryResults
import com.sgg.healthykaly.service.RecipeService
import com.sgg.healthykaly.utils.QueryBuilder
import com.sgg.healthykaly.utils.QueryConstants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipeRepository @Inject constructor(
        private val recipeService: RecipeService,
        private val recipeDatabase: RecipeDatabase
) {
    @OptIn(ExperimentalPagingApi::class)
    fun getRecipes(query: Map<String, Int> = QueryBuilder.defaultQuery): Flow<PagingData<RecipeEntity>> {
        val pagingSourceFactory = {
            recipeDatabase.recipeDao()
                    .findAllRecipe()
        }
        return Pager(config = PagingConfig(pageSize = QueryConstants.DEFAULT_LOAD_SIZE,
                                           enablePlaceholders = false),
                     remoteMediator = RecipeRemoteMediator(queries = query,
                                                           recipeService = recipeService,
                                                           recipeDatabase = recipeDatabase),
                     pagingSourceFactory = pagingSourceFactory).flow

    }

    suspend fun getRecipe(id: Int): RecipeEntity {
        return recipeDatabase.recipeDao()
                .findOneById(id)
    }

    suspend fun getRecipeSummary(recipeId: Int): Flow<SummaryResults> {
        return flow {
            emit(SummaryResults.Loading())
            try {
                val result = recipeService.findRecipeSummary(recipeId)
                emit(SummaryResults.Success(result))
            } catch (e: Exception) {
                emit(SummaryResults.Error(e.message))
            }
        }
    }
}