package com.sgg.healthykaly.di

import com.sgg.healthykaly.data.DatabaseProvider
import com.sgg.healthykaly.data.RecipeDataSourceProvider
import com.sgg.healthykaly.data.RemoteDataProvider
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


@Module
@InstallIn(SingletonComponent::class)
abstract class DatabaseDataProviderModule {
    @DatabaseSource
    @Singleton
    @Binds
    abstract fun provideDataSourceProvider(databaseProvider: DatabaseProvider): RecipeDataSourceProvider

}

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataProviderModule {
    @RemoteSource
    @Singleton
    @Binds
    abstract fun provideDataSourceProvider(remoteDataProvider: RemoteDataProvider): RecipeDataSourceProvider
}