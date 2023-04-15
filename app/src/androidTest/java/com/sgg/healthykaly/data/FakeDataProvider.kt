package com.sgg.healthykaly.data

import androidx.paging.PagingData
import com.sgg.healthykaly.model.RecipeModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class FakeDataProvider @Inject constructor() : RecipeDataSourceProvider {
    override fun getFlowOfRecipes(queries: Map<String, Int>): Flow<PagingData<RecipeModel>> {
        val pagingRecipe = PagingData.from(fakeRecipeList)
        return flowOf(pagingRecipe)
    }
}