package com.sgg.healthykaly.data

import androidx.paging.PagingSource
import androidx.room.*
import com.sgg.healthykaly.model.RecipeEntity

@Dao
interface RecipeDao {

    @Query("SELECT * FROM recipes")
    fun findAllRecipe(): PagingSource<Int, RecipeEntity>

    @Query("SELECT * FROM recipes WHERE id=:id")
    suspend fun findOneById(id: Int): RecipeEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOne(recipe: RecipeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(recipes: List<RecipeEntity>)

    @Delete
    suspend fun deleteRecipe(recipe: RecipeEntity)

    @Query("DELETE FROM recipes")
    suspend fun deleteAllRecipe()

    @Update
    suspend fun updateRecipe(recipe: RecipeEntity)
}