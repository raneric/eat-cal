package com.sgg.healthykaly.viewmodel

import androidx.lifecycle.*
import androidx.paging.PagingData
import com.sgg.healthykaly.model.Recipe
import com.sgg.healthykaly.repository.NetworkRecipeRepository
import com.sgg.healthykaly.repository.RecipeRepository
import com.sgg.healthykaly.utils.LoadingState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class RecipeViewModel(private val recipeRepository: NetworkRecipeRepository,
                      private val savedStateHandle: SavedStateHandle? = null) : ViewModel() {

    private lateinit var _recipes: Flow<PagingData<Recipe>>
    val recipes: Flow<PagingData<Recipe>>
        get() = _recipes

    init {
        loadRecipe()
    }

    private fun loadRecipe() {
        // Launch a coroutine in the ViewModel scope
        viewModelScope.launch {
            // Load recipes from the repository and update the LiveData object
            _recipes = recipeRepository.getFlowOfRecipe()
        }
    }

    fun reloadData() {
        loadRecipe()
    }
}