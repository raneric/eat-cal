package com.sgg.healthykaly

import androidx.lifecycle.ViewModelProvider
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sgg.healthykaly.model.Recipe
import com.sgg.healthykaly.repository.RecipeRepository
import com.sgg.healthykaly.utils.LoadingState
import com.sgg.healthykaly.viewmodel.RecipeViewModel
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FindRecipeUiTest {
    private lateinit var fakeRepositoryWithEmptyList: RecipeRepository

    @Before
    fun createFakeRepository() {
        fakeRepositoryWithEmptyList = object : RecipeRepository {
            override suspend fun getAllRecipe(): List<Recipe> {
                return emptyList()
            }
        }
    }

    @Test
    fun testEmptyRecipeListFromRepository() {
        val senario = ActivityScenario.launch(MainActivity::class.java)
        senario.onActivity { activity ->
            val viewModel = ViewModelProvider(activity,
                                              Injection.provideRecipeViewModelWithFakeRepository(
                                                  activity,
                                                  fakeRepositoryWithEmptyList))[RecipeViewModel::class.java]
           assertEquals(LoadingState.ERROR, viewModel.loadingState.value)
        }
    }
}