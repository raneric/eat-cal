package com.sgg.healthykaly.di

import com.sgg.healthykaly.data.FakeDataProvider
import com.sgg.healthykaly.data.RecipeDataSourceProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
annotation class RemoteSource

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RemoteDataProviderModule::class]
)
abstract class FakeDatabaseDataProviderModule {
    @RemoteSource
    @Singleton
    @Binds
    abstract fun provideDataSourceProvider(fakeDataProvider: FakeDataProvider
    ): RecipeDataSourceProvider
}