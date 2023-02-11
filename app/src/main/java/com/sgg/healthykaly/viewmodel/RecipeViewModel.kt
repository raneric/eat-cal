package com.sgg.healthykaly.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sgg.healthykaly.model.Recipe
import com.sgg.healthykaly.service.RecipeApi
import com.sgg.healthykaly.util.LoadingState
import kotlinx.coroutines.launch

class RecipeViewModel : ViewModel() {
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
            try {
                _recipes.value = RecipeApi.recipeService.findByFat(DEFAULT_FAT)
                _loadingState.value = LoadingState.DONE
            } catch (e: Exception) {
                Log.d("Retrofit Error", e.toString())
                _loadingState.value = LoadingState.ERROR
            }
        }
    }

    fun reloadData(){
        loadRecipe()
    }

    companion object {
        const val DEFAULT_FAT = 25
    }
}