package com.sgg.healthykaly

import androidx.lifecycle.ViewModelProvider
import androidx.savedstate.SavedStateRegistryOwner
import com.sgg.healthykaly.repository.NetworkRecipeRepository
import com.sgg.healthykaly.repository.RecipeRepository
import com.sgg.healthykaly.service.RecipeApi
import com.sgg.healthykaly.viewmodel.RecipeViewModel
import com.sgg.healthykaly.viewmodel.RecipeViewModelFactory

object Injection {

    private fun provideNetworkRepository(): NetworkRecipeRepository {
        return NetworkRecipeRepository(RecipeApi.recipeService)
    }

    fun provideRecipeViewModel(owner: SavedStateRegistryOwner): ViewModelProvider.Factory {
        return RecipeViewModelFactory(owner, provideNetworkRepository())
    }

    fun provideRecipeViewModelWithFakeRepository(owner: SavedStateRegistryOwner,fakeRepository:RecipeRepository): ViewModelProvider.Factory {
        return RecipeViewModelFactory(owner, fakeRepository)
    }
}