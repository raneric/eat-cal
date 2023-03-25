package com.sgg.healthykaly

import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagingData
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sgg.healthykaly.model.RecipeModel
import com.sgg.healthykaly.repository.RecipeDataSourceProvider
import com.sgg.healthykaly.repository.RecipeRepository
import com.sgg.healthykaly.ui.viewmodel.RecipeViewModel
import kotlinx.coroutines.flow.Flow
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FindRecipeUiTestModel {
    private lateinit var fakeDatasourceProvider: RecipeDataSourceProvider

    private lateinit var repositoryWIthFakeDataSource: RecipeRepository

    @Before
    fun createDataSource() {
        fakeDatasourceProvider = object : RecipeDataSourceProvider {
            override fun getFlowOfRecipes(queries: Map<String, Int>): Flow<PagingData<RecipeModel>> {
                TODO("Not yet implemented")
            }
        }
        repositoryWIthFakeDataSource = RecipeRepository((fakeDatasourceProvider))
    }

    @Test
    fun testEmptyRecipeListFromRepository() {
        /*val senario = ActivityScenario.launch(MainActivity::class.java)
        senario.onActivity { activity ->
            val viewModel = ViewModelProvider(activity,
                                              Injection.provideRecipeViewModelWithFakeRepository(
                                                  activity,
                                                  repositoryWIthFakeDataSource))[RecipeViewModel::class.java]

        }*/
    }
}

