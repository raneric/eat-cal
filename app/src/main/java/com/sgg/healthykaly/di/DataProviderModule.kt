package com.sgg.healthykaly.di

import com.sgg.healthykaly.repository.DatabaseProvider
import com.sgg.healthykaly.repository.RecipeDataSourceProvider
import com.sgg.healthykaly.repository.RemoteDataProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
annotation class DatabaseSource

@Qualifier
annotation class RemoteSource

@InstallIn(SingletonComponent::class)
@Module
abstract class DatabaseDataProviderModule {
    @DatabaseSource
    @Singleton
    @Binds
    abstract fun provideDataSourceProvider(databaseProvider: DatabaseProvider): RecipeDataSourceProvider

}

@InstallIn(SingletonComponent::class)
@Module
abstract class RemoteDataProviderModule {
    @RemoteSource
    @Singleton
    @Binds
    abstract fun provideDataSourceProvider(remoteDataProvider: RemoteDataProvider): RecipeDataSourceProvider
}