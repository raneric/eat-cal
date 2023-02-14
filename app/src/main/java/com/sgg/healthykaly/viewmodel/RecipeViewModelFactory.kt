package com.sgg.healthykaly.viewmodel

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.sgg.healthykaly.repository.RecipeRepository

class RecipeViewModelFactory(owner: SavedStateRegistryOwner,
                             private val recipeRepository: RecipeRepository
) :
        AbstractSavedStateViewModelFactory(owner, null) {
    override fun <T : ViewModel?> create(key: String,
                                         modelClass: Class<T>,
                                         handle: SavedStateHandle): T {
        if (modelClass.isAssignableFrom(RecipeViewModel::class.java)) {
            return RecipeViewModel(recipeRepository, handle) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class  ${modelClass::class.simpleName}")
    }

}