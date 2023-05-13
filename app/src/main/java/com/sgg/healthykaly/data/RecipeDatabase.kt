package com.sgg.healthykaly.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sgg.healthykaly.model.RecipeEntity
import com.sgg.healthykaly.model.RemotePageEntity

@Database(entities = [RecipeEntity::class, RemotePageEntity::class], version = 2)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao

    abstract fun remotePageDao(): RemotePageDao
}