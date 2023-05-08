package com.sgg.healthykaly.model

data class RecipeModel(
        val id: Int,
        val title: String,
        val image: String,
        val imageType: String,
        val calories: Int?,
        val protein: String?,
        val fat: String?,
        val carbs: String?) {
}

fun RecipeModel.asDatabaseEntity(): RecipeEntity {
    return RecipeEntity(
        id = this.id,
        title = this.title,
        image = this.image,
        imageType = this.imageType,
        calories = this.calories,
        protein = this.protein,
        fat = this.fat,
        carbs = this.carbs
    )
}