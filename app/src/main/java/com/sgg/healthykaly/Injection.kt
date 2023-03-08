package com.sgg.healthykaly

import androidx.lifecycle.ViewModelProvider
import androidx.savedstate.SavedStateRegistryOwner
import com.sgg.healthykaly.repository.RecipeDataSourceProvider
import com.sgg.healthykaly.repository.RecipeRepository
import com.sgg.healthykaly.repository.RemoteDataProvider
import com.sgg.healthykaly.service.RecipeApi
import com.sgg.healthykaly.viewmodel.RecipeViewModelFactory

object Injection {

    private fun provideRemoteDataSource(): RemoteDataProvider {
        return RemoteDataProvider(RecipeApi.recipeService)
    }

    private fun provideRepository(): RecipeRepository {
        return RecipeRepository(provideRemoteDataSource())
    }

    fun provideRecipeViewModel(owner: SavedStateRegistryOwner): ViewModelProvider.Factory {
        return RecipeViewModelFactory(owner, provideRepository())
    }

    fun provideRecipeViewModelWithFakeRepository(owner: SavedStateRegistryOwner,
                                                 repository: RecipeRepository): ViewModelProvider.Factory {
        return RecipeViewModelFactory(owner, repository)
    }
}