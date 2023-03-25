package com.sgg.healthykaly.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sgg.healthykaly.model.RecipeEntity

@Database(entities = [RecipeEntity::class], version = 1)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao

    companion object {
        private var INSTANCE: RecipeDatabase? = null

        fun getInstance(context: Context): RecipeDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDb(context)
        }

        private fun buildDb(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                                     RecipeDatabase::class.java,
                                     "HealthyKaly.db")
                        .build()
    }
}