package com.sgg.healthykaly.data

import androidx.room.*
import com.sgg.healthykaly.model.RecipeEntity

@Dao
interface RecipeDao {

    @Query("SELECT * FROM recipes")
    suspend fun findAllRecipe(): List<RecipeEntity>

    @Query("SELECT * FROM recipes WHERE id=:id")
    suspend fun findOneById(id: Int): RecipeEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOne(recipe: RecipeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg recipes: RecipeEntity)

    @Delete
    suspend fun deleteRecipe(recipe: RecipeEntity)

    @Update
    suspend fun updateRecipe(recipe: RecipeEntity)
}