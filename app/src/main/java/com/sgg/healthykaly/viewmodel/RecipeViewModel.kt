package com.sgg.healthykaly.viewmodel

import androidx.lifecycle.*
import com.sgg.healthykaly.model.Recipe
import com.sgg.healthykaly.repository.RecipeRepository
import com.sgg.healthykaly.utils.LoadingState
import kotlinx.coroutines.launch

class RecipeViewModel(private val recipeRepository: RecipeRepository,
                      private val savedStateHandle: SavedStateHandle? = null) : ViewModel() {
    private var _recipes = MutableLiveData<List<Recipe>>()
    val recipes: LiveData<List<Recipe>>
        get() = _recipes

    private var _loadingState = MutableLiveData<LoadingState>()
    val loadingState: MutableLiveData<LoadingState>
        get() = _loadingState

    init {
        loadRecipe()
    }

    private fun loadRecipe() {
        viewModelScope.launch {
            _loadingState.value = LoadingState.LOADING
            _recipes.value = recipeRepository.getAllRecipe()

            if (recipes.value?.isNotEmpty()!!) {
                _loadingState.value = LoadingState.DONE
            } else {
                _loadingState.value = LoadingState.ERROR
            }
        }
    }

    fun reloadData() {
        loadRecipe()
    }


}