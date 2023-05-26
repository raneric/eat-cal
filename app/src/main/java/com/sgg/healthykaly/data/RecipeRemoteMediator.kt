package com.sgg.healthykaly.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.sgg.healthykaly.model.RecipeEntity
import com.sgg.healthykaly.model.RemotePageEntity
import com.sgg.healthykaly.model.asDatabaseEntity
import com.sgg.healthykaly.service.RecipeService
import com.sgg.healthykaly.utils.QueryConstants.PARAM_PAGE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class RecipeRemoteMediator(private val queries: Map<String, Int>,
                           private val recipeService: RecipeService,
                           private val recipeDatabase: RecipeDatabase) :
        RemoteMediator<Int, RecipeEntity>() {

    private val recipeDao = recipeDatabase.recipeDao()
    private val remotePageDao = recipeDatabase.remotePageDao()

    override suspend fun load(loadType: LoadType,
                              state: PagingState<Int, RecipeEntity>): MediatorResult {

        return try {
            val newQuery = queries.toMutableMap()
            val loadPage = when (loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val remotePage = getRemoteKey()

                    if (remotePage.nextPage == null) {
                        return MediatorResult.Success(endOfPaginationReached = true)
                    }
                    remotePage.nextPage
                }
            }
            loadPage?.let {
                newQuery[PARAM_PAGE] = it
            }

            val recipes = recipeService.findByNutriments(newQuery)
                    .map { it.asDatabaseEntity() }

            recipeDatabase.withTransaction {
                val nextPage = newQuery[PARAM_PAGE]!!.plus(6)
                remotePageDao.deleteRemotePage()
                remotePageDao.insertOne(RemotePageEntity(nextPage = nextPage))
                if (loadType == LoadType.REFRESH) {
                    recipeDao.deleteAllRecipe()
                }
                recipeDao.insertAll(recipes = recipes)
            }
            MediatorResult.Success(endOfPaginationReached = recipes.isEmpty())
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }

    }

    private suspend fun getRemoteKey(): RemotePageEntity {
        return withContext(Dispatchers.IO) {
            remotePageDao.getRemotePage()
        }
    }
}