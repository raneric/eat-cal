package com.sgg.healthykaly.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sgg.healthykaly.model.Recipe
import com.sgg.healthykaly.service.RecipeService

class RecipePagingSource(private val recipeService: RecipeService,
                         private val query: String) : PagingSource<Int, Recipe>() {
    override fun getRefreshKey(state: PagingState<Int, Recipe>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Recipe> {
        TODO("Not yet implemented")
    }

}