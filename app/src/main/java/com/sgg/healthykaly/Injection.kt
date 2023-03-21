package com.sgg.healthykaly

import androidx.lifecycle.ViewModelProvider
import androidx.savedstate.SavedStateRegistryOwner
import com.sgg.healthykaly.repository.RecipeRepository
import com.sgg.healthykaly.repository.RemoteDataProvider
import com.sgg.healthykaly.service.RecipeApi
import com.sgg.healthykaly.ui.viewmodel.RecipeViewModelFactory

object Injection {

    /**
     * A methode that provide a emote data source with an instance of RecipeApi service
     */
    private fun provideRemoteDataSource(): RemoteDataProvider {
        return RemoteDataProvider(RecipeApi.recipeService)
    }

    /**
     * A methode that provide a repository with a datasource dependence
     */
    private fun provideRepository(): RecipeRepository {
        return RecipeRepository(provideRemoteDataSource())
    }

    /**
     * A method that crate and return an instance of RecipeViewModel
     * with a repository and the appropriate remote data source
     */
    fun provideRecipeViewModel(owner: SavedStateRegistryOwner): ViewModelProvider.Factory {
        return RecipeViewModelFactory(owner, provideRepository())
    }

}