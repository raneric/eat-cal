package com.sgg.healthykaly.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sgg.healthykaly.model.Recipe
import com.sgg.healthykaly.service.RecipeService
import com.sgg.healthykaly.utils.QueryConstants.DEFAULT_LOAD_SIZE
import com.sgg.healthykaly.utils.QueryConstants.PARAM_PAGE
import com.sgg.healthykaly.utils.QueryConstants.INITIAL_PAGE
import retrofit2.HttpException
import java.io.IOException

class RecipePagingSource(private val recipeService: RecipeService,
                         private val query: Map<String, Int>) : PagingSource<Int, Recipe>() {
    override fun getRefreshKey(state: PagingState<Int, Recipe>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                    ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Recipe> {
        val currentPage = params.key ?: INITIAL_PAGE
        val loadSize = params.loadSize
        val nextQuery = query.toMutableMap()
        nextQuery[PARAM_PAGE] = currentPage
        val nextKey = currentPage + (loadSize / DEFAULT_LOAD_SIZE)

        return try {
            val recipeResponse = recipeService.findByNutriments(nextQuery)
            LoadResult.Page(data = recipeResponse,
                            prevKey = if (currentPage == INITIAL_PAGE) null else currentPage,
                            nextKey = nextKey)
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}