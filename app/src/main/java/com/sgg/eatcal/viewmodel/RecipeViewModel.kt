package com.sgg.eatcal.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sgg.eatcal.model.Recipe
import com.sgg.eatcal.service.SpoonacularApi
import kotlinx.coroutines.launch

class RecipeViewModel : ViewModel() {
    private var _recipes = MutableLiveData<List<Recipe>>()
    val recipes: LiveData<List<Recipe>>
        get() = _recipes


    init {
        loadRecipe()
    }

    private fun loadRecipe() {
        viewModelScope.launch {

            try {
                _recipes.value = SpoonacularApi.spoonacularService.testRequst().results
                Log.d("Retrofit error", recipes.value!!.get(0).title)
            } catch (e: Exception) {
                Log.d("Retrofit ok", e.toString())
            }
        }
    }
}