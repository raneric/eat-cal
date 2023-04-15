package com.sgg.healthykaly.ui.viewmodel

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sgg.healthykaly.model.RecipeModel
import com.sgg.healthykaly.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject


class RecipeViewModel @Inject constructor(private val recipeRepository: RecipeRepository) : ViewModel() {

    private lateinit var _recipes: Flow<PagingData<RecipeModel>>
    val recipes: Flow<PagingData<RecipeModel>>
        get() = _recipes

    init {
        loadInitState()
    }

    private fun loadInitState() {
        viewModelScope.launch {
            _recipes = recipeRepository.getRecipes()
                    .cachedIn(viewModelScope)
        }
    }
}