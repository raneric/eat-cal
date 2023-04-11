package com.sgg.healthykaly.di

import android.content.Context
import androidx.room.Room
import com.sgg.healthykaly.data.RecipeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private var INSTANCE: RecipeDatabase? = null

    @Singleton
    @Provides
    fun provideDatabase(
            @ApplicationContext context: Context): RecipeDatabase = INSTANCE ?: synchronized(this) {
        INSTANCE ?: buildDb(context)
    }

    private fun buildDb(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                                 RecipeDatabase::class.java,
                                 "HealthyKaly.db")
                    .build()


}