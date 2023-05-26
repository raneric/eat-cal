package com.sgg.healthykaly.ui.viewmodel

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sgg.healthykaly.model.RecipeEntity
import com.sgg.healthykaly.model.RecipeSummaryModel
import com.sgg.healthykaly.model.SummaryResults
import com.sgg.healthykaly.repository.RecipeRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


class RecipeViewModel @Inject constructor(private val recipeRepository: RecipeRepository) :
        ViewModel() {

    private lateinit var _recipes: Flow<PagingData<RecipeEntity>>
    val recipes: Flow<PagingData<RecipeEntity>>
        get() = _recipes

    val recipeSummary: MutableStateFlow<SummaryResults> = MutableStateFlow(SummaryResults.Loading())

    var selectedRecipeId: Int? = null

    init {
        loadInitState()
    }

    suspend fun refreshSummary() {
        selectedRecipeId?.let {
            getRecipeSummary(it).collect { result ->
                recipeSummary.value = result
            }
        }
    }

    suspend fun getRecipe(id: Int): RecipeEntity {
        return recipeRepository.getRecipe(id)
    }

    suspend fun getRecipeSummary(id: Int): StateFlow<SummaryResults> {
        return recipeRepository.getRecipeSummary(id)
                .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), SummaryResults.Loading())
    }

    private fun loadInitState() {
        viewModelScope.launch {
            _recipes = recipeRepository.getRecipes()
                    .cachedIn(viewModelScope)
        }
    }
}