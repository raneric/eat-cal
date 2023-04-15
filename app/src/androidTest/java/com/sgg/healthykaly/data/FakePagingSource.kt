package com.sgg.healthykaly.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sgg.healthykaly.model.RecipeModel
import com.sgg.healthykaly.utils.QueryConstants
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOf

class FakePagingSource : PagingSource<Int, RecipeModel>() {

    override fun getRefreshKey(state: PagingState<Int, RecipeModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                    ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RecipeModel> {
        val currentPage = params.key ?: QueryConstants.INITIAL_PAGE
        val loadSize = params.loadSize
        val nextKey = currentPage + (loadSize / QueryConstants.DEFAULT_LOAD_SIZE)
        val recipeResponse = provideListOfRecipe()
        return LoadResult.Page(data = recipeResponse,
                               prevKey = if (currentPage == QueryConstants.INITIAL_PAGE) null else currentPage,
                               nextKey = nextKey)
    }


    private suspend fun provideListOfRecipe(): List<RecipeModel> {
        delay(5000)
        return listOf(
            RecipeModel(
                id = 632105,
                title = "Almond Cacao Nut Balls",
                image = "https://spoonacular.com/recipeImages/632105-312x231.jpg",
                imageType = "jpg",
                calories = 63,
                protein = "2g",
                fat = "4g",
                carbs = "6g"
            ),
            RecipeModel(
                id = 632884,
                title = "Asian Soft Scrambled Eggs",
                image = "https://spoonacular.com/recipeImages/632884-312x231.jpg",
                imageType = "jpg",
                calories = 106,
                protein = "8g",
                fat = "7g",
                carbs = "1g"
            ),
            RecipeModel(
                id = 635248,
                title = "Blackberry Walnut Cookies",
                image = "https://spoonacular.com/recipeImages/635248-312x231.jpg",
                imageType = "jpg",
                calories = 142,
                protein = "2g",
                fat = "6g",
                carbs = "19g"
            ),
            RecipeModel(
                id = 636926,
                title = "Cantaloupe Soup With Crispy Ham and Basil",
                image = "https://spoonacular.com/recipeImages/636926-312x231.jpg",
                imageType = "jpg",
                calories = 132,
                protein = "3g",
                fat = "6g",
                carbs = "16g"
            ),
            RecipeModel(
                id = 636970,
                title = "Caramel Almond Berry Trifle",
                image = "https://spoonacular.com/recipeImages/636970-312x231.jpg",
                imageType = "jpg",
                calories = 191,
                protein = "5g",
                fat = "4g",
                carbs = "34g"
            ),
            RecipeModel(
                id = 637184,
                title = "Carrot Cake Pancakes",
                image = "https://spoonacular.com/recipeImages/637184-312x231.jpg",
                imageType = "jpg",
                calories = 143,
                protein = "4g",
                fat = "5g",
                carbs = "19g"
            ),
            RecipeModel(
                id = 639900,
                title = "Colcannon",
                image = "https://spoonacular.com/recipeImages/639900-312x231.jpg",
                imageType = "jpg",
                calories = 163,
                protein = "5g",
                fat = "6g",
                carbs = "19g"
            )
        )
    }
}